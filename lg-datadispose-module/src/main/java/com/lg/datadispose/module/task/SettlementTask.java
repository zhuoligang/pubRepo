package com.lg.datadispose.module.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lg.datadispose.module.bean.po.CenterUserMain;
import com.lg.datadispose.module.conf.RedisCache;
import com.lg.datadispose.module.constant.DataDisposeConstant;
import com.lg.datadispose.module.dao.userdao.CenterUserMainMapper;
import com.lg.datadispose.module.exception.BusinessException;
import com.lg.datadispose.module.service.IntegralService;
import com.lg.datadispose.module.service.VipLevelService;

/**
 * 
 * @ClassName: SettlementTask
 * @Description: TODO(积分，vip结算任务)
 * @author zlg
 * @date 2019年8月5日上午9:41:32
 *
 */
@Component
public class SettlementTask {
	private final static Logger logger = LoggerFactory.getLogger(SettlementTask.class);
	
	@Autowired
	private RedisCache redisCache;
	
	@Autowired
	private IntegralService integralService;
	@Autowired
	private VipLevelService vipLevelService;
	
	@Autowired
	private CenterUserMainMapper centerUserMainDao;
	
	
	@Async("initTaskAsyncPool")
	public void executeAsync() {
		try {
			//本次需要执行的用户
			CenterUserMain centerUserMain = null;
			while (true){//如果出错，需要重新手动开启
				//获取上次执行用户的memberId记录
				String key_lastMember = "Settlement:lastMember:";
				String lastMemberId = this.redisCache.get(key_lastMember);
				//1.如果有上次处理记录，则需要处理上次之后的一个用户
				centerUserMain = this.centerUserMainDao.selectLastMember(lastMemberId);
				if(centerUserMain == null){
					logger.info(">>>>>>>>>>>>>>>>>>>已处理完所有用户<<<<<<<<<<<<<<<<<<");
					this.redisCache.deleteCache(key_lastMember);
					return;
				}
				//2.结算该用户的积分
				this.integralService.settlementOfIntegral(centerUserMain);
				//3.结算该用户的vip相关
				this.vipLevelService.settlementOfVipLevel(centerUserMain);
				//4.记录本次处理memberId记录，作为下次的依据
				this.redisCache.set(key_lastMember, centerUserMain.getMemberId());
				logger.info("用户：" + centerUserMain.getMemberId() +" 积分、vip结算已完成。。。");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(DataDisposeConstant.CODE_500, e.getMessage());
		}
	}

}
