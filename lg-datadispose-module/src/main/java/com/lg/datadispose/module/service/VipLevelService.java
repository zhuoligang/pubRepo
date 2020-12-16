package com.lg.datadispose.module.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lg.datadispose.module.bean.bo.Day30AlongBo;
import com.lg.datadispose.module.bean.po.CenterUserMain;
import com.lg.datadispose.module.bean.po.CenterViplevelBase;
import com.lg.datadispose.module.bean.po.CenterViplevelExplain;
import com.lg.datadispose.module.conf.RedisCache;
import com.lg.datadispose.module.constant.DataDisposeConstant;
import com.lg.datadispose.module.dao.assetdao.AssetAccountMapper;
import com.lg.datadispose.module.dao.assetdao.AssetHotAccountRecordMapper;
import com.lg.datadispose.module.dao.userdao.CenterViplevelBaseMapper;
import com.lg.datadispose.module.exception.BusinessException;
import com.lg.datadispose.module.util.DataDisposeUtil;

@Service
public class VipLevelService {
	private final static Logger logger = LoggerFactory.getLogger(VipLevelService.class);
	@Autowired
	private RedisCache redisCache;

	@Autowired
	private AssetAccountMapper assetAccountDao;
	@Autowired
	private AssetHotAccountRecordMapper assetHotAccountRecordDao;
	@Autowired
	private CenterViplevelBaseMapper centerViplevelBaseDao;

	/*
	 * 查询用户bt持仓量
	 */
	public BigDecimal getUserBtCount(String userId) {
		BigDecimal userBtCount = BigDecimal.ZERO;
		try {
			userBtCount = this.assetAccountDao.selectBtCountByUserId(userId);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		}

		return userBtCount;
	}

	/*
	 * 查询用户最近30天成交折算CNT值（以当前币种最新价格折算）
	 */
	public BigDecimal getTurnoverDay30(String userId) {
		BigDecimal turnoverDay30 = BigDecimal.ZERO;
		try {
			Date day30Along = DataDisposeUtil.Day30Along(new Date(), 30);
			Map<String, Object> sendMap = new HashMap<>();
			sendMap.put("userId", userId);
			sendMap.put("day30Along", day30Along);
			// 获取用户30天内的所有成交
			List<Day30AlongBo> day30AlongBo = this.assetHotAccountRecordDao.getDay30AlongBo(sendMap);
			if (day30AlongBo != null && day30AlongBo.size() > 0) {
				for (Day30AlongBo day30AlongBo2 : day30AlongBo) {
					// 每个币种的成交总量--->折算成cnt价值
					String newPriceKey = "Market:NewPrice:";
					String newPriceHashKey = day30AlongBo2.getCoinCode().toUpperCase();
					if ("CNT".equals(newPriceHashKey)) {
						turnoverDay30 = turnoverDay30.add(day30AlongBo2.getSumCount());
					} else {
						Object hGet = this.redisCache.hGet(newPriceKey + "CNT", newPriceHashKey);
						if (hGet == null || "".equals(hGet)) {// 可能cnt交易区没有
							hGet = this.redisCache.hGet(newPriceKey + "USDT", newPriceHashKey);
						}
						if (hGet == null || "".equals(hGet)) {// 可能USDT交易区没有
							hGet = this.redisCache.hGet(newPriceKey + "BTC", newPriceHashKey);
						}
						if (hGet == null || "".equals(hGet)) {// 可能BTC交易区没有
							hGet = this.redisCache.hGet(newPriceKey + "ETH", newPriceHashKey);
						}
						if (hGet == null || "".equals(hGet)) {// 所有交易区都没有，默认0
							hGet = BigDecimal.ZERO;
						}
						BigDecimal cntCount_ = day30AlongBo2.getSumCount().multiply(new BigDecimal(hGet.toString()));
						turnoverDay30 = turnoverDay30.add(cntCount_);
					}
				}
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		}

		return turnoverDay30;
	}

	/*
	 * 用户vip等级结算
	 */
	public void settlementOfVipLevel(CenterUserMain centerUserMain) {
		try {
			// 默认用户为普通用户
			Integer vipLevel = -1;
			// 1.获取用户BT持仓量
			BigDecimal userBtCount = getUserBtCount(centerUserMain.getId());
			if(userBtCount == null ){
				userBtCount = BigDecimal.ZERO;
			}
			// 2.获取用户近30天成交折算CNT量
			BigDecimal turnoverDay30 = getTurnoverDay30(centerUserMain.getId());
			// 3.VIP等级划分
			if (checkVipLevel(userBtCount, turnoverDay30, 8)) { // 从最高等级开始排级
				vipLevel = 8;
			} else if (checkVipLevel(userBtCount, turnoverDay30, 7)) {
				vipLevel = 7;
			} else if (checkVipLevel(userBtCount, turnoverDay30, 6)) {
				vipLevel = 6;
			} else if (checkVipLevel(userBtCount, turnoverDay30, 5)) {
				vipLevel = 5;
			} else if (checkVipLevel(userBtCount, turnoverDay30, 4)) {
				vipLevel = 4;
			} else if (checkVipLevel(userBtCount, turnoverDay30, 3)) {
				vipLevel = 3;
			} else if (checkVipLevel(userBtCount, turnoverDay30, 2)) {
				vipLevel = 2;
			} else if (checkVipLevel(userBtCount, turnoverDay30, 1)) {
				vipLevel = 1;
			}

			// 4.记录vip等级
			CenterViplevelBase cvb = new CenterViplevelBase();
			cvb.setId(DataDisposeUtil.createUUId());
			cvb.setUserId(centerUserMain.getId());
			cvb.setMemberId(centerUserMain.getMemberId());
			cvb.setVipLevel(vipLevel);
			this.centerViplevelBaseDao.insertSelective(cvb);
			
			//5.redis缓存vip等级
			String vipKey = DataDisposeConstant.VIPLEVEL_USER_LEVEL + centerUserMain.getId();
			this.redisCache.set(vipKey, String.valueOf(vipLevel));
			//临时缓存2天
			this.redisCache.expire(vipKey, 2, TimeUnit.DAYS);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		}

	}

	private boolean checkVipLevel(BigDecimal userBtCount, BigDecimal turnoverDay30, int level) {
		boolean bool = true;
		// 获取vip等级要求
		String vipKey = DataDisposeConstant.VIPLEVEL_EXPLAIN;
		Object hGet = this.redisCache.hGet(vipKey, String.valueOf(level));
		CenterViplevelExplain cve = JSONObject.parseObject(hGet.toString(), CenterViplevelExplain.class);
		if (userBtCount.compareTo(cve.getBtFloor()) < 0 || turnoverDay30.compareTo(cve.getTradingFloor()) < 0) {
			bool = false;
		}
		return bool;
	}

}
