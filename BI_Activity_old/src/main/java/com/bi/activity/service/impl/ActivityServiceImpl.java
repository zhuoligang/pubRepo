/**
 * ActivityServiceImpl.java
 * com.bi.activity.service.impl
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年9月4日 		zhuoligang
 *
 * Copyright (c) 2018, b-i All Rights Reserved.
*/

package com.bi.activity.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bi.activity.dto.AdminLog;
import com.bi.activity.dto.BaseAutowired;
import com.bi.activity.dto.StaticticsTask;
import com.bi.activity.dto.StatisticsInvitationRegUser;
import com.bi.activity.dto.StatisticsRegUser;
import com.bi.activity.entity.ActivityConf;
import com.bi.activity.entity.ActivityDataPoints;
import com.bi.activity.entity.ActivityIndex;
import com.bi.activity.entity.ActivityTail;
import com.bi.activity.entity.Member;
import com.bi.activity.entity.MemberVirtualcoin;
import com.bi.activity.entity.Ranking;
import com.bi.activity.entity.Vcoinrecord;
import com.bi.activity.service.ActivityService;
import com.bi.activity.util.MathHelper;
import com.bi.activity.util.ZPageUtil;


/**
 * ClassName:ActivityServiceImpl（Describe this Class）
 * 
 * @author zhuoligang
 * @version Ver 1.0
 * @Date 2018年9月4日 下午4:59:30
 * @see
 */
@Service
public class ActivityServiceImpl extends BaseAutowired implements ActivityService {

	@Override
	public Boolean checkActivity(Map<String, Object> map) {
		Boolean bool = false;
		Integer activityId = map.get("activityId") == null ? 1 : Integer.parseInt(map.get("activityId").toString());
		ActivityIndex activityIndex = activityIndexDao.selectByPrimaryKey(activityId);
		if (activityIndex.getAcResidue() <= 17001) {
			System.out.println("奖励已发放完毕------------------------------");
			return bool;
		}
		Date starttime = activityIndex.getAcStarttime();
		Date endtime = activityIndex.getAcEndtime();
		Integer memberId = Integer.parseInt(map.get("memberId").toString());
		Member member = memberDao.selectByPrimaryKey(memberId);
		Date regTime = null;
		if (member == null) {
			regTime = new Date();
		} else {
			regTime = member.getRegtime();
		}
		if (!regTime.before(starttime) && regTime.before(endtime)) {
			ActivityDataPoints activityDataPoints = activityDataPointsDao.selectByMemberId(memberId);
			if (activityDataPoints != null) {
				// 最高普通奖励等级3，达到3后当作没有普通奖励领奖资格
				if (activityDataPoints.getConfCode() < 3)
					bool = true;
			} else {
				bool = true;
			}
		}

		return bool;

	}

	@Override
	public Boolean points(Map<String, Object> map) {
		Boolean bool = true;
		ActivityDataPoints adp = JSON.parseObject(JSON.toJSONString(map), ActivityDataPoints.class);
		adp.setConfCode(0);
		adp.setSpecialCode(0);
		int i = activityDataPointsDao.insertSelective(adp);
		if (i < 1)
			return false;

		return bool;

	}

	@Override
	public List<ActivityIndex> findActivitys(Map<String, Object> map) {
		List<ActivityIndex> list = new ArrayList<ActivityIndex>();
		if (map != null && map.get("activityId") != null) {
			ActivityIndex activityIndex = activityIndexDao
					.selectByPrimaryKey(Integer.parseInt(map.get("activityId").toString()));
			list.add(activityIndex);
		} else {
			list = activityIndexDao.selectActivityIndexs();
		}
		return list;

	}

	@Override
	public List<ActivityConf> findActivityConfs(Map<String, Object> map) {
		return activityConfDao.selectActivityConfs(map);

	}

	@Override
	public String award(Map<String, Object> map) {
		String result = "ok";
		// 活动代码 indexId
		int indexId = Integer.parseInt(map.get("indexId").toString());
		// 完成任务id confId
		int confId = Integer.parseInt(map.get("confId").toString());
		// 检测奖池数量底线
		ActivityIndex activityIndex = activityIndexDao.selectByPrimaryKey(indexId);
		if (activityIndex.getAcResidue() <= 17001) {
			System.out.println("奖励已发放完毕------------------------------");
			return result = "奖励已发放完毕";
		}

		// 任务完成人id
		int memberId = Integer.parseInt(map.get("memberId").toString());
		Member member = memberDao.selectByPrimaryKey(memberId);
		// 数据埋点--- 检测用户是否按照顺序完成任务
		ActivityDataPoints activityDataPoints = activityDataPointsDao.selectByMemberId(memberId);
		if (confId <= 3) {
			if (activityDataPoints == null) {
				System.out.println("不是活动期间注册用户------------------------------");
				return result = "不是活动期间注册用户";
			}
			if (activityDataPoints.getConfCode() < 3 && confId != (activityDataPoints.getConfCode() + 1)) {
				System.out.println("不能重复完成任务或者不按顺序完成任务------------------------------");
				return result = "不能重复完成任务或者不按顺序完成任务";
			}
		} else {
			// 特殊任务完成，有可能是活动时间以外的老用户完成
			if (activityDataPoints == null) {
				activityDataPoints = new ActivityDataPoints();
				activityDataPoints.setConfCode(0);
				activityDataPoints.setIndexCode(indexId);
				activityDataPoints.setMemberId(String.valueOf(memberId));
				activityDataPoints.setParentId(String.valueOf(member.getParentId()));
				activityDataPoints.setMemberTel(member.getMobile());
				activityDataPoints.setRegIp("");
				activityDataPoints.setRegTime(member.getRegtime());
				// 特殊任务
				activityDataPoints.setSpecialCode(confId);
				int m = activityDataPointsDao.insertSelective(activityDataPoints);
				if (m < 1)
					return result = "临界值奖励无法发放";
			}
		}
		// ----------------------------------------奖励发放start---------------------------------------------
		// 本次发放数
		int confCoinCount = 0;
		// 奖励币种
		int confCoinId = 2;
		ActivityConf activityConf = null;
		boolean bool = false;
		if (confId == 1) {// 完成任务1
			activityConf = activityConfDao.selectByPrimaryKey(1);
			confCoinCount = activityConf.getConfCoinCount();
			// 检测本次任务发放后奖池是否满足底线
			if ((activityIndex.getAcResidue() - confCoinCount) < 17000) {
				System.out.println("临界值奖励无法发放------------------------------");
				return result = "临界值奖励无法发放";
			}

			// 奖励币种
			confCoinId = activityConf.getConfCoinId();
			// 完成任务者获得奖励
			bool = awardDo(member.getId(), confCoinId, confCoinCount);
			if (bool) {
				result = awardDoAfter(activityIndex, activityDataPoints, member.getId(), member.getId(), indexId,
						confId, confCoinId, confCoinCount);
			}

			if (member.getParentId() > 0) {// 有邀请人还要发放邀请人奖励
				activityIndex = activityIndexDao.selectByPrimaryKey(indexId);
				activityConf = activityConfDao.selectByPrimaryKey(4);
				confCoinCount = activityConf.getConfCoinCount();
				// 检测本次任务发放后奖池是否满足底线
				if ((activityIndex.getAcResidue() - confCoinCount) < 17000) {
					System.out.println("临界值奖励无法发放------------------------------");
					return result = "临界值奖励无法发放";
				}

				// 奖励币种
				confCoinId = activityConf.getConfCoinId();
				// 完成任务者获得奖励
				bool = awardDo(member.getParentId(), confCoinId, confCoinCount);
				if (bool) {
					result = awardDoAfter(activityIndex, activityDataPoints, member.getParentId(), member.getId(),
							indexId, confId, confCoinId, confCoinCount);
				}
			}
		} else {// 完成任务2、3
			activityConf = activityConfDao.selectByPrimaryKey(confId);
			confCoinCount = activityConf.getConfCoinCount();
			// 检测本次任务发放后奖池是否满足底线
			if ((activityIndex.getAcResidue() - confCoinCount) < 17000) {
				System.out.println("临界值奖励无法发放------------------------------");
				return result = "临界值奖励无法发放";
			}

			// 奖励币种
			confCoinId = activityConf.getConfCoinId();
			// 完成任务者获得奖励
			bool = awardDo(member.getParentId(), confCoinId, confCoinCount);
			if (bool) {
				result = awardDoAfter(activityIndex, activityDataPoints, member.getParentId(), member.getId(), indexId,
						confId, confCoinId, confCoinCount);
			}
		}
		// ----------------------------------------奖励发放end---------------------------------------------

		return result;

	}

	/**
	 * 
	 * @Title: awardDo @Description: TODO(奖励发放) @param @param memberId
	 *         获奖id @param @param confCoinId 奖励币种 @param @param awardCount
	 *         获奖数量 @param @return 设定文件 @return boolean 返回类型 @throws
	 */
	public boolean awardDo(int memberId, int confCoinId, int confCoinCount) {
		Boolean bool = false;
		BigDecimal awardCount = new BigDecimal(confCoinCount);
		Map<String, Object> map_ = new HashMap<String, Object>();
		map_.put("memberId", memberId);
		map_.put("virtualcoinId", confCoinId);
		List<MemberVirtualcoin> memberVirtualcoins = memberVirtualcoinDao.selectByMemberId(map_);
		MemberVirtualcoin memberVirtualcoin = new MemberVirtualcoin();
		if (memberVirtualcoins != null && memberVirtualcoins.size() > 0) {// 已有奖励币账户
			memberVirtualcoin.setId(memberVirtualcoins.get(0).getId());
			memberVirtualcoin.setCountactive(
					MathHelper.add(memberVirtualcoins.get(0).getCountactive(), awardCount, MathHelper.CAL_PRECISION));
			int i = memberVirtualcoinDao.updateByPrimaryKeySelective(memberVirtualcoin);
			if (i < 1)
				return false;
			bool = true;
		} else {// 没有奖励币账户
			memberVirtualcoin = new MemberVirtualcoin(memberId, confCoinId, awardCount, BigDecimal.ZERO, null);
			int i = memberVirtualcoinDao.insertSelective(memberVirtualcoin);
			if (i < 1)
				return false;
			bool = true;
		}
		return bool;
	}

	/**
	 * 
	 * @Title: awardDoAfter @Description: TODO(这里用一句话描述这个方法的作用) @param @param
	 *         activityIndex 活动 @param @param activityDataPoints
	 *         埋点数据 @param @param memberId 获奖人id @param @param parentId
	 *         任务完成人id @param @param indexId 活动id @param @param confId
	 *         任务id @param @param confCoinId 币种id @param @param confCoinCount
	 *         币种数量 @param @return 设定文件 @return String 返回类型 @throws
	 */
	public String awardDoAfter(ActivityIndex activityIndex, ActivityDataPoints activityDataPoints, int memberId,
			int parentId, int indexId, int confId, int confCoinId, int confCoinCount) {
		String result = "ok";
		BigDecimal awardCount = new BigDecimal(confCoinCount);

		Vcoinrecord vcoinrecord = new Vcoinrecord(memberId, confCoinId, 97, awardCount, BigDecimal.ZERO, new Date());
		int w = vcoinrecordDao.insertSelective(vcoinrecord);
		if (w < 1)
			return result = "奖励记录写入错误";
		activityDataPoints.setIndexCode(indexId);
		if (confId <= 3) {
			activityDataPoints.setConfCode(confId);
		} else {
			activityDataPoints.setSpecialCode(confId);
		}
		int m = activityDataPointsDao.updateByPrimaryKeySelective(activityDataPoints);
		if (m < 1)
			return result = "数据埋点写入错误";
		ActivityIndex activityIndex_ = new ActivityIndex();
		activityIndex_.setId(activityIndex.getId());
		activityIndex_.setAcResidue(activityIndex.getAcResidue() - confCoinCount);
		int k = activityIndexDao.updateByPrimaryKeySelective(activityIndex_);
		if (k < 1)
			return result = "奖池剩余写入错误";
		ActivityTail activityTail = new ActivityTail(memberId, parentId, indexId, confId, confCoinId, confCoinCount,
				new Date());
		int i = activityTailDao.insertSelective(activityTail);
		if (i < 1)
			return result = "奖励发放详情写入错误";

		return result;
	}

	@Override
	public List<Ranking> ranking(Map<String, Object> map) {
		List<Ranking> rankings = activityDataPointsDao.ranking(map);
		if (rankings != null && rankings.size() > 0) {
			int rankingCount = Integer.parseInt(map.get("rankingCount").toString());
			int i = 1;
			for (Ranking ranking : rankings) {
				if (i > rankingCount)
					break;
				ranking.setId(i);
				i += 1;
				String memberId = ranking.getMemberId();
				String a = memberId.substring(0, 2);
				String b = ranking.getMemberId().substring(memberId.length() - 1);
				String c = a + "***" + b;
				ranking.setMemberId(c);
			}
		}
		return rankings;
	}

	@Override
	public Ranking rankingByMemberId(Map<String, Object> map) {
		int memberId = Integer.parseInt(map.get("memberId").toString());
		ActivityDataPoints activityDataPoints = activityDataPointsDao.selectByMemberId(memberId);
		if (activityDataPoints == null) {
			Member member = memberDao.selectByPrimaryKey(memberId);
			// 数据埋点、
			Map<String, Object> map_ = new HashMap<String, Object>();
			map_.put("memberId", member.getId());
			map_.put("parentId", member.getParentId());
			map_.put("memberTel", member.getMobile());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map_.put("regTime", sdf.format(member.getRegtime()));
			map_.put("regIp", "");
			map_.put("indexCode", "1");
			boolean bool = points(map_);
			System.out.println("数据埋点 " + bool);
		}
		// 有多少人邀请好友数比用户多
		int id = activityTailDao.selectRankingByMemberId(map);
		Ranking ranking = null;
		ranking = activityDataPointsDao.selectRankingByMemberId(map);
		if (ranking == null || id > 998 ) {
			return ranking = new Ranking(0, String.valueOf(memberId), 0, 0);
		}
		if (id < 20) {// 20名内不允许并列排名
			Map<String, Object> map_ = new HashMap<String, Object>();
			map_.put("rankingCount","20");
			List<Ranking> rankings = activityDataPointsDao.ranking(map_);
			if (rankings != null && rankings.size() > 0) {
				int i = 1;
				for (Ranking rank : rankings) {
					if (rank.getMemberId().equals(String.valueOf(memberId))) {
						ranking.setId(i);
						break;
					}
					i += 1;
				}
			}
		} else {
			ranking.setId(id + 1);
		}
		return ranking;
	}

	@Override
	public ZPageUtil<ActivityDataPoints> friends(Map<String, Object> map) {
		ZPageUtil<ActivityDataPoints> result_cache = new ZPageUtil<ActivityDataPoints>();
		// 总条数
		int count = activityDataPointsDao.friendsCount(map);
		map.put("count", count);
		// 得到分页查询条件map
		result_cache = ZPageUtil.newMap(map, ActivityDataPoints.class);
		List<ActivityDataPoints> activityDataPoints = activityDataPointsDao.friends(map);
		Map<Integer, Object> MAP = ActivityExplain.getInstance().getMAP();

		for (ActivityDataPoints activityDataPoint : activityDataPoints) {
			String memberId = String.valueOf(activityDataPoint.getMemberId());
			String a = memberId.substring(0, 2);
			String b = memberId.substring(memberId.length() - 1);
			String c = a + "***" + b;
			activityDataPoint.setMemberId(c);
			StringBuffer sb = new StringBuffer();
			int confCode = activityDataPoint.getConfCode();
			if (confCode == 1) {
				sb.append((String) MAP.get(1));
			} else if (confCode == 2) {
				sb.append((String) MAP.get(1) + "、");
				sb.append((String) MAP.get(2));
			} else if (confCode == 3) {
				sb.append((String) MAP.get(1) + "、");
				sb.append((String) MAP.get(2) + "、");
				sb.append((String) MAP.get(3));
			}
			activityDataPoint.setConfName(sb.toString());
		}
		result_cache.setList(activityDataPoints);
		return result_cache;
	}

	@Override
	public ZPageUtil<ActivityTail> friendsAward(Map<String, Object> map) {
		ZPageUtil<ActivityTail> result_cache = new ZPageUtil<ActivityTail>();
		// 总条数
		int count = activityTailDao.friendsAwardCount(map);
		map.put("count", count);
		// 得到分页查询条件map
		result_cache = ZPageUtil.newMap(map, ActivityTail.class);
		List<ActivityTail> activityTails = activityTailDao.friendsAward(map);
		Map<Integer, Object> MAP = ActivityExplain.getInstance().getMAP();

		int memberId = Integer.parseInt(map.get("memberId").toString());
		for (ActivityTail activityTail : activityTails) {
			StringBuffer sb = new StringBuffer();
			int parentId = activityTail.getParentId();
			int confCode = activityTail.getConfCode();
			if (memberId == parentId) {
				sb.append("个人");
			} else {
				sb.append("好友");
			}
			if (confCode == 1) {
				sb.append((String) MAP.get(1) + "奖励");
			} else if (confCode == 2) {
				sb.append((String) MAP.get(2) + "奖励");
			} else if (confCode == 3) {
				sb.append((String) MAP.get(3) + "奖励");
			}
			activityTail.setConfName(sb.toString());
		}
		result_cache.setList(activityTails);
		return result_cache;
	}

	@Override
	public boolean findNewUserByDay(String dayStart, String dayEnd, String url) {
		List<StatisticsRegUser> selectNewUserByDay = activityDataPointsDao.selectNewUserByDay(dayStart, dayEnd);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet createSheet = wb.createSheet();

		HSSFRow row1 = createSheet.createRow(0);
		row1.createCell(0).setCellValue("用户ID");
		row1.createCell(1).setCellValue("姓名");
		row1.createCell(2).setCellValue("性别");
		row1.createCell(3).setCellValue("手机号");
		row1.createCell(4).setCellValue("注册时间点");
		row1.createCell(5).setCellValue("注册IP");
		row1.createCell(6).setCellValue("是否被邀请用户(1为被邀请)");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (selectNewUserByDay != null) {
			for (int i = 1; i <= selectNewUserByDay.size(); i++) {
				if (selectNewUserByDay.get(i - 1) != null) {
					Row nrow = createSheet.createRow(i);
					// 用户ID
					Cell ncell = nrow.createCell(0);
					ncell.setCellValue(selectNewUserByDay.get(i - 1).getMemberId());
					// 姓名
					ncell = nrow.createCell(1);
					String name = "";
					if (selectNewUserByDay.get(i - 1).getName() != null
							&& !selectNewUserByDay.get(i - 1).getName().equals("")
							&& selectNewUserByDay.get(i - 1).getName().length() >= 1) {
						name = selectNewUserByDay.get(i - 1).getName().substring(0, 1) + "*";
						ncell.setCellValue(name);
					} else {
						ncell.setCellValue(name);
					}
					// 性别
					ncell = nrow.createCell(2);
					String idcard = selectNewUserByDay.get(i - 1).getIdcard();
					String sex = "";
					if (idcard != null && !idcard.equals("")) {
						if (idcard.length() == 15) {
							sex = idcard.substring(14, 15);
						} else if (idcard.length() == 18) {
							sex = idcard.substring(16, 17);
						} else {
							sex = "#";
						}

					} else {
						sex = "#";
					}
					Pattern pattern = Pattern.compile("^-?[0-9]+");
					if (pattern.matcher(sex).matches()&&!sex.equals("#")) {
						if (Integer.valueOf(sex) % 2 == 0) {
							ncell.setCellValue("女");
						} else {
							ncell.setCellValue("男");
						}
					} else {
						ncell.setCellValue("未知");
					}
					// 手机号
					ncell = nrow.createCell(3);
					ncell.setCellValue(selectNewUserByDay.get(i - 1).getMemberTel());
					// 注册时间点
					ncell = nrow.createCell(4);
					ncell.setCellValue(simpleDateFormat.format(selectNewUserByDay.get(i - 1).getRegTime()));
					// 注册IP
					ncell = nrow.createCell(5);
					ncell.setCellValue(selectNewUserByDay.get(i - 1).getRegIp());
					// 是否被邀请用户
					ncell = nrow.createCell(6);
					ncell.setCellValue(selectNewUserByDay.get(i - 1).getParentId().equals("0") ? 0 : 1);
				}
			}
		}
		String[] split = dayEnd.split(" ");
		File file = new File(url + "/ROOT/uploads/statictics/first/" + split[0] + "_statictics_first.xls");
		System.out.println(url + "/ROOT/uploads/statictics/first/" + split[0] + "_statictics_first.xls");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream stream = FileUtils.openOutputStream(file);
			wb.write(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (file.exists()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean findActiveTimeByHour(String dayStart, String dayEnd, String url) {
		// TODO Auto-generated method stub
		List<AdminLog> selectActiveTimeByHour = activityDataPointsDao.selectActiveTimeByHour(dayStart, dayEnd);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet createSheet = wb.createSheet();

		HSSFRow row1 = createSheet.createRow(0);
		row1.createCell(0).setCellValue("时间");
		row1.createCell(1).setCellValue("登陆数量");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (selectActiveTimeByHour != null) {
			for (int i = 1; i <= selectActiveTimeByHour.size(); i++) {
				if (selectActiveTimeByHour.get(i - 1) != null) {
					Row nrow = createSheet.createRow(i);
					Cell ncell = nrow.createCell(0);
					ncell.setCellValue(selectActiveTimeByHour.get(i - 1).getHour() + ":00-"
							+ selectActiveTimeByHour.get(i - 1).getHour() + ":59");
					ncell = nrow.createCell(1);
					ncell.setCellValue(selectActiveTimeByHour.get(i - 1).getNum());
				}
			}
		}
		String[] split = dayEnd.split(" ");
		File file = new File(url + "/ROOT/uploads/statictics/second/" + split[0] + "_statictics_second.xls");
		System.out.println(url + "/ROOT/uploads/statictics/second/" + split[0] + "_statictics_second.xls");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream stream = FileUtils.openOutputStream(file);
			wb.write(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (file.exists()) {
			return true;
		}
		else {
			return false;
		}

	}

	@Override
	public boolean findInvitationRegUserByDay(String dayStart, String dayEnd, String url) {
		// TODO Auto-generated method stub
		// 被邀请注册人数
		List<StatisticsInvitationRegUser> selectIsInvitationRegUserByDay = activityDataPointsDao
				.selectIsInvitationRegUserByDay(dayStart, dayEnd);
		// 自主注册人数
		List<StatisticsInvitationRegUser> selectNotInvitationRegUserByDay = activityDataPointsDao
				.selectNotInvitationRegUserByDay(dayStart, dayEnd);

		StatisticsInvitationRegUser IsInvitationRegUser = null;
		StatisticsInvitationRegUser NotInvitationRegUser = null;
		Integer isInvitition = 0;
		Integer notInvitition = 0;
		if (selectIsInvitationRegUserByDay != null) {
			IsInvitationRegUser = selectIsInvitationRegUserByDay.get(0);
			if (IsInvitationRegUser != null) {
				isInvitition = IsInvitationRegUser.getIsInvitition();
			}
		}
		if (selectNotInvitationRegUserByDay != null) {
			NotInvitationRegUser = selectNotInvitationRegUserByDay.get(0);
			if (NotInvitationRegUser != null) {
				notInvitition = NotInvitationRegUser.getNotInvitition();
			}
		}

		// 写xls
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet createSheet = wb.createSheet();
		HSSFRow row1 = createSheet.createRow(0);
		row1.createCell(0).setCellValue("被邀请注册用户数");
		row1.createCell(1).setCellValue("自主注册用户数");
		Row row2 = createSheet.createRow(1);
		Cell ncell = row2.createCell(0);
		ncell.setCellValue(isInvitition);
		ncell = row2.createCell(1);
		ncell.setCellValue(notInvitition);

		String[] split = dayEnd.split(" ");
		File file = new File(
				url + "/ROOT/uploads/statictics/thirdAndFourth/" + split[0] + "_statictics_thirdAndFourth.xls");
		System.out.println(
				url + "/ROOT/uploads/statictics/thirdAndFourth/" + split[0] + "_statictics_thirdAndFourth.xls");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream stream = FileUtils.openOutputStream(file);
			wb.write(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (file.exists()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean findInvitationRegUserTask(String dayEnd, String url) {
		// TODO Auto-generated method stub
		List<StaticticsTask> selectInvitationRegUserTask = activityDataPointsDao.selectInvitationRegUserTask(dayEnd);
		// 写xls
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet createSheet = wb.createSheet();
		HSSFRow row1 = createSheet.createRow(0);
		row1.createCell(0).setCellValue("完成任务情况");
		row1.createCell(1).setCellValue("完成人数");
		if (selectInvitationRegUserTask != null) {

			for (int i = 1; i <= selectInvitationRegUserTask.size(); i++) {
				if (selectInvitationRegUserTask.get(i - 1) != null) {
					Row nrow = createSheet.createRow(i);
					Cell ncell = nrow.createCell(0);
					switch (selectInvitationRegUserTask.get(i - 1).getTask()) {
					case "1":
						ncell.setCellValue("完成了高级实名认证任务");
						break;
					case "2":
						ncell.setCellValue("完成了累计充值任务");
						break;
					case "3":
						ncell.setCellValue("完成了累计交易任务");
						break;

					default:
						ncell.setCellValue("其他");
						break;
					}
					ncell = nrow.createCell(1);
					ncell.setCellValue(selectInvitationRegUserTask.get(i - 1).getNum());
				}
			}
		}
		String[] split = dayEnd.split(" ");
		File file = new File(url + "/ROOT/uploads/statictics/fifth/" + split[0] + "_statictics_fifth.xls");
		System.out.println(url + "/ROOT/uploads/statictics/fifth/" + split[0] + "_statictics_fifth.xls");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream stream = FileUtils.openOutputStream(file);
			wb.write(stream);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (file.exists()) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
