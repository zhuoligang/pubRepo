package com.bi.activity.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.bi.activity.dao.TodayLoginLogMapper;
import com.bi.activity.util.SpringContextUtil;

/**
 * 
* @ClassName: MemberLoginJob
* @Description: TODO(用户登录定时任务)
* @author zhuoligang
* @date 2018年10月9日上午10:50:45
*
 */
public class MemberLoginJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		String str = "";
		TodayLoginLogMapper todayLoginLogMapper = (TodayLoginLogMapper) SpringContextUtil.getBean(TodayLoginLogMapper.class);
		try {
			todayLoginLogMapper.deleteAll();
			str = "------------------------执行成功------------------------";
		} catch (Exception e) {
			str = "------------------------执行失败------------------------";
			e.printStackTrace();
		}
		System.out.println(str);
	}
	
	
	
}
