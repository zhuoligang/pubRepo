package com.lg.datadispose.module.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class DataDisposeUtil {
	public static void main(String[] args) {
		Calendar comp_cal = Calendar.getInstance();
		comp_cal.set(2019, 5 - 1, 1, 0, 0, 0);
		Date comp_date = comp_cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(comp_date));
		
		System.out.println(reqMonth(comp_date));
		
//		Date date = new Date();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		
//		System.out.println(cal.get(Calendar.DATE));
//		System.out.println(comp_cal.get(Calendar.DATE));
//		//day_<0 说明天数还不够一个月,month_需要-1
//		int day_ =  cal.get(Calendar.DATE) - comp_cal.get(Calendar.DATE);
//		int month_ = cal.get(Calendar.MONTH) - comp_cal.get(Calendar.MONTH);
//		int year_ = (cal.get(Calendar.YEAR) - comp_cal.get(Calendar.YEAR)) * 12;
//		System.out.println(Math.abs(year_ + month_));

	}
	
	
	/**
	 * uuid生产
	 * 
	 * @return
	 */
	public static String createUUId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/*
	 * 到8月1日为止，用户注册有几个月
	 */
	public static int reqMonth(Date regTime){
		int monthCount = 0;
		//8月1日
		Calendar comp_cal = Calendar.getInstance();
		comp_cal.set(2019, 8 - 1, 1, 0, 0, 0);
		//注册时间
		Calendar reg_cal = Calendar.getInstance();
		reg_cal.setTime(regTime);
		
		int day_ = 0;
		int month_ = 0;
		int year_ = (comp_cal.get(Calendar.YEAR) - reg_cal.get(Calendar.YEAR)) * 12;
		if(year_ < 0){//注册年份已在设定年份之后
			return monthCount;
		}else{
			month_ = comp_cal.get(Calendar.MONTH) - reg_cal.get(Calendar.MONTH);
			if(month_ <= 0){//注册月份已在设定月份或之后
				return monthCount;
			}else{
				day_ = comp_cal.get(Calendar.DATE) - reg_cal.get(Calendar.DATE);
				if(day_ < 0){//说明天数还不够一个月,month_需要-1
					month_ -= 1;
				}
			}
		}
		
		return year_ + month_;
	}

	/*
	 * 根据时间获取指定天数之前的当前时间
	 */
	public static Date Day30Along(Date date,int day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -day);
		return cal.getTime();
	}
}
