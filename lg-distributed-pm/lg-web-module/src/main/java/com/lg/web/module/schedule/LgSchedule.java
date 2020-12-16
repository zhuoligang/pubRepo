package com.lg.web.module.schedule;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lg.web.module.conf.RedisCache;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.exception.BusinessException;
import com.lg.web.module.websocket.WebsocketServer;

@Component
public class LgSchedule {
	private final static Logger logger = LoggerFactory.getLogger(LgSchedule.class);

	@Autowired
	private RedisCache redisCache;

	// @Scheduled(cron = "0/10 * * * * ?")
	// @Scheduled(fixedDelay = 10000)
	@Async("initSchedulingTaskExecutor")
	public void robotEntrustJob() {
		logger.info("定时任务。。。。。。" + new Date().toString());
	}

	@Scheduled(cron = "0 0/1 * * * ?")
	@Async("initSchedulingTaskExecutor")
	public void oneMinutesOptionsJob() {
		logger.info("一分钟期权定时任务。。。。。。" + new Date().toString());
		this.redisCache.setCache("minutes_options:one:", "1");
		this.redisCache.expire("minutes_options:one:", 60, TimeUnit.SECONDS);
	}
	
	@Scheduled(cron = "0 0/2 * * * ?")
	@Async("initSchedulingTaskExecutor")
	public void twoMinutesOptionsJob() {
		logger.info("二分钟期权定时任务。。。。。。" + new Date().toString());
		this.redisCache.setCache("minutes_options:two:", "2");
		this.redisCache.expire("minutes_options:two:", 120, TimeUnit.SECONDS);
	}
	
	@Scheduled(cron = "0 0/5 * * * ?")
	@Async("initSchedulingTaskExecutor")
	public void fiveMinutesOptionsJob() {
		logger.info("五分钟期权定时任务。。。。。。" + new Date().toString());
		this.redisCache.setCache("minutes_options:five:", "5");
		this.redisCache.expire("minutes_options:five:", 300, TimeUnit.SECONDS);
	}

	@Scheduled(fixedDelay = 1000)
	@Async("initSchedulingTaskExecutor")
	public void oneMinutesOptionsNoticeJob() {
		// logger.info("一分钟期权定时通知任务。。。。。。" + new Date().toString());
		Long oneExpire = this.redisCache.getExpire("minutes_options:one:", TimeUnit.SECONDS);
		Long twoExpire = this.redisCache.getExpire("minutes_options:two:", TimeUnit.SECONDS);
		Long fiveExpire = this.redisCache.getExpire("minutes_options:five:", TimeUnit.SECONDS);
		try {
			if (oneExpire >= 10) {
//				System.out.println("--one----" + oneExpire);
				WebsocketServer.sendInfo(String.valueOf(oneExpire - 10L), "one");
			}
			if (twoExpire >= 10) {
//				System.out.println("--two----" + twoExpire);
				WebsocketServer.sendInfo(String.valueOf(twoExpire - 10L), "two");
			}
			if (fiveExpire >= 10) {
//				System.out.println("--five----" + fiveExpire);
				WebsocketServer.sendInfo(String.valueOf(fiveExpire - 10L), "five");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		}
	}
}
