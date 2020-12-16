package com.bi.activity.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.bi.activity.service.ActivityService;
import com.bi.activity.service.impl.ActivityServiceImpl;
import com.bi.activity.util.SpringContextUtil;

public class ProcessRunSchedJob extends QuartzJobBean {
	
    @Override
    protected void executeInternal(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
        // ApplicationContext appCtx =
        // SpringContextUtil.getApplicationContext();
        // IUserService iUserService = appCtx.getBean(IUserService.class);
        // iUserService.test();
    	System.out.println("执行了定时器");
    	ActivityService activityService = (ActivityService)SpringContextUtil.getBean(ActivityService.class);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date now = new Date();
    	//获取昨天
    	Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.roll(Calendar.DATE, -1);
        Date date = c.getTime();
        //昨天开始时间
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND,00);
        c.set(Calendar.MILLISECOND, 00);
        Date timeStart =c.getTime();
        String dayStart = sf.format(timeStart); 
        //昨天结束时间
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        Date timeEnd=c.getTime();
        String dayEnd=sf.format(timeEnd);
        String path = this.getClass().getResource("/").getPath();
        String[] split = path.split("/WEB-INF");
        String tmp=split[0];
        String url = tmp.substring(0, tmp.lastIndexOf("/"));

    	activityService.findNewUserByDay(dayStart,dayEnd,url);
    	activityService.findActiveTimeByHour(dayStart,dayEnd,url);
    	activityService.findInvitationRegUserByDay(dayStart,dayEnd,url);
    	activityService.findInvitationRegUserTask(dayEnd,url);
    	

    }

}
