package com.bi.activity.util;

import java.math.BigDecimal;

public class BigDecimalExUtils {
	
	public BigDecimalExUtils() {
		
	}
	/**
	 * 计算 total 可以分为 以 unit 为最小计量单位的 个数（不够一个 unit 计量的，按 一个 unit 计算） 
	 * @param total
	 * @param unit
	 * @return
	 */
	public static int GetCountByUnit(BigDecimal total, BigDecimal unit) {
		int c = 0;
		if(total != null && 
				unit != null && 
				total.doubleValue() > 0 && 
				unit.doubleValue() > 0) {
			BigDecimal m = total.divide(unit);
			if(total.doubleValue() % unit.doubleValue() > 0) {
				c += m.setScale(0,BigDecimal.ROUND_DOWN).intValue() + 1;
			}else{
				c += m.setScale(0,BigDecimal.ROUND_DOWN).intValue();
			}
		}
		return c;
	}
	
	public static int GetCountByUnit(double total, double unit) {
		int c = 0;
		if(total > 0 && unit > 0) {
			int m = (int)(total / unit);
			if(total % unit > 0) {
				c += m + 1;
			}else{
				c += m;
			}
		}
		return c;
	}
	
	public static double[] GetArrayByUnit(double total, double unit) {
		int len = GetCountByUnit(total,unit);
		double[] arr = new double[len];
		if(total >= unit) {
			int i = 0;
			double m = total;
			m = m - unit;
			while(m >= 0){
				arr[i] = unit;
				i = i + 1;
				if(m >= unit) {
					m = m - unit;
				}else{
					if(m > 0) {
						arr[i] = m;
					}
					break;
				}
			}
		}else{
			arr[0] = total;
		}
		return arr;
	}
	/**
	 * 转为指定数度的浮动点数
	 * @param src
	 * @param newScale
	 * @param roundingMode
	 * @return
	 */
	public static double convertTodouble(BigDecimal src, int newScale, int roundingMode) {
		double d = 0;
		if(src != null) {
			d = src.setScale(newScale, roundingMode).doubleValue();
		}
		return d;
	}
	/**
	 * 转化为以“万”单位的 BigDecimal
	 * @param src
	 * @return
	 */
	public static BigDecimal convertToTenThousandUnits(BigDecimal src) {
		if(src != null) {
			return src.divide(new BigDecimal(10000));
		}else{
			return new BigDecimal(0);
		}
	}
	
	/**
	 * 取较小的值
	 */
	public static BigDecimal min(BigDecimal com1, BigDecimal com2) {
	    return com1.compareTo(com2) < 0 ? com1 : com2;
	}
	
	/**
     * 取较大的值
     */
    public static BigDecimal max(BigDecimal com1, BigDecimal com2) {
        return com1.compareTo(com2) > 0 ? com1 : com2;
    }
    
    /**
     * 格式化BigDecimal为0或者保留小数位数的0以及为null的情况，输出str,否则输出原值的字符串
     */
    public static String formatBigDecimal(BigDecimal com,String str){
    	int i=0;
    	if(com!=null){
    		if(com.toString().split("\\.").length==1){
    			i=0;
    		}else{
    			System.out.println(com.toString().split("\\."));
    			i=com.toString().split("\\.")[1].length();
    		}
    	}
    	if(com==null){
    		return str;
    	}else if(com.compareTo(BigDecimal.ZERO)==0){
    		return str;
    	}else if(com.compareTo(new BigDecimal(0).setScale(i))==0){
    		return str;
    	}else{
    		return com.toString();
    	}
    }
    
}
