package com.lg.datadispose.module.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lg.datadispose.module.bean.po.CenterIntegralBase;
import com.lg.datadispose.module.bean.po.CenterIntegralDetail;
import com.lg.datadispose.module.bean.po.CenterIntegralExplain;
import com.lg.datadispose.module.bean.po.CenterUserMain;
import com.lg.datadispose.module.conf.RedisCache;
import com.lg.datadispose.module.constant.DataDisposeConstant;
import com.lg.datadispose.module.dao.userdao.CenterIntegralBaseMapper;
import com.lg.datadispose.module.dao.userdao.CenterIntegralDetailMapper;
import com.lg.datadispose.module.dao.userdao.CenterIntegralExplainMapper;
import com.lg.datadispose.module.exception.BusinessException;
import com.lg.datadispose.module.util.DataDisposeUtil;


@Service
public class IntegralService {
	private final static Logger logger = LoggerFactory.getLogger(IntegralService.class);
	@Autowired
	private RedisCache redisCache;
	
	@Autowired
	private CenterIntegralExplainMapper centerIntegralExplainDao;
	@Autowired
	private CenterIntegralBaseMapper centerIntegralBaseDao;
	@Autowired
	private CenterIntegralDetailMapper centerIntegralDetailDao;
	
	public PageInfo<CenterIntegralExplain> queryExplanIntegral(Map<String, String> map) {
		PageInfo<CenterIntegralExplain> pageinfo = null;
		try {
			if (map == null) {
				map = new HashMap<>();
			}
			if (map.get("pageNumber") == null) {
				map.put("pageNumber", "1");
			}
			if (map.get("pageSize") == null) {
				map.put("pageSize", "10");
			}
			PageHelper.startPage(Integer.parseInt(map.get("pageNumber")), Integer.parseInt(map.get("pageSize")));
			List<CenterIntegralExplain> list = integralExplainDo();
			//方案2---不做分页
//			list = this.centerIntegralExplainDao.queryAll();
			
			pageinfo = new PageInfo<>(list);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			logger.error("queryExplanIntegral?" + JSONObject.toJSONString(map));
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("queryExplanIntegral?" + JSONObject.toJSONString(map));
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		}
		return pageinfo;
	}
	
	private List<CenterIntegralExplain> integralExplainDo() {
		List<CenterIntegralExplain> list = new ArrayList<CenterIntegralExplain>();;
		try {
			String explainKey = "integral_explain:";
			Map<Object, Object> resultMap = this.redisCache.hGetAll(explainKey);
			if (resultMap != null && !resultMap.isEmpty()) {
				for (Entry<Object, Object> entry : resultMap.entrySet()) {
//					CenterIntegralExplain cie = JSONObject.parseObject(entry.getValue().toString(), CenterIntegralExplain.class);
					list.add(JSONObject.parseObject(entry.getValue().toString(), CenterIntegralExplain.class));
				}
			} else {
				list = this.centerIntegralExplainDao.queryAll();
				Map<String, String> maps = new HashMap<String, String>();
				for (CenterIntegralExplain centerIntegralExplain : list) {
					maps.put(String.valueOf(centerIntegralExplain.getId()),
							JSONObject.toJSONString(centerIntegralExplain));
				}
				this.redisCache.hPutAll(explainKey, maps);
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		}
		return list;
	}

	public void settlementOfIntegral(CenterUserMain centerUserMain) {
		try {
		//用户结算后总计应该增加的积分数
		BigDecimal integralCount = BigDecimal.ZERO;
//		CenterUserMain centerUserMain = this.centerUserMainDao.selectByPrimaryKey(userId);
		
		//1.普通实名认证积分结算+2分
		if((centerUserMain.getName() != null && !"".equals(centerUserMain.getName())) &&
				(centerUserMain.getIdcard() != null && !"".equals(centerUserMain.getIdcard()))){
			integralCount = integralCount.add(new BigDecimal(2));
		}
		//2.高级实名认证积分结算+5分
		if(centerUserMain.getIdcardPicCheckType() == 1 && centerUserMain.getIdcardPicCheckId() == 3){
			integralCount = integralCount.add(new BigDecimal(5));
		}
		//3.注册时长积分结算:1-3个月：10;4-6个月：20;7-9个月：30;10个月以上：50
		int reqMonth = DataDisposeUtil.reqMonth(centerUserMain.getRegTime());
		if(reqMonth >= 10){
			integralCount = integralCount.add(new BigDecimal(50));
		}else if(reqMonth >= 7){
			integralCount = integralCount.add(new BigDecimal(30));
		}else if(reqMonth >= 4){
			integralCount = integralCount.add(new BigDecimal(20));
		}else if(reqMonth >= 1){
			integralCount = integralCount.add(new BigDecimal(10));
		}
		
		//总分得出---->有积分增加的用户需要结算
		if(integralCount.compareTo(BigDecimal.ZERO) > 0){
			CenterIntegralBase centerIntegralBase = new CenterIntegralBase();
			centerIntegralBase.setId(DataDisposeUtil.createUUId());
			centerIntegralBase.setUserId(centerUserMain.getId());
			centerIntegralBase.setMemberId(centerUserMain.getMemberId());
			centerIntegralBase.setIntegral(integralCount);
			//1.增加积分
			this.centerIntegralBaseDao.insertSelective(centerIntegralBase);
			//2.记录日志
			CenterIntegralDetail centerIntegralDetail = new CenterIntegralDetail();
			centerIntegralDetail.setId(DataDisposeUtil.createUUId());
			centerIntegralDetail.setUserId(centerUserMain.getId());
			centerIntegralDetail.setMemberId(centerUserMain.getMemberId());
			centerIntegralDetail.setIsincrease(true);
			centerIntegralDetail.setStatus(999);
			centerIntegralDetail.setScore(integralCount);
			centerIntegralDetail.setOccurTime(new Date());
			this.centerIntegralDetailDao.insert(centerIntegralDetail);
		}
		
	} catch (BusinessException e) {
		logger.error(e.getMessage(), e);
		throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
	}
		
	}
	
	
}