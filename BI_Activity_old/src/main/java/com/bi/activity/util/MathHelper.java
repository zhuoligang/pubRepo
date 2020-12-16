package com.bi.activity.util;

import java.math.BigDecimal;

/**
 * 数学帮助类
 * 
 * @author 洪坤成
 *
 */
public class MathHelper {

    public final static int CAL_PRECISION = 9;// 虚拟币交易计算精度

    public final static int RMB_PRECISION = 9;// 交易人民币账户精度

    /**
     * 截取小数位 name:(Describe the function of this method) @return @throws
     */
    private static BigDecimal subBigDecimalDown(BigDecimal b, int scale) {
        return b.add(BigDecimal.ZERO).setScale(scale, BigDecimal.ROUND_DOWN);
    }

    /**
     * 截取小数位 name:(Describe the function of this method) @return @throws
     */
    private static BigDecimal subBigDecimalUp(BigDecimal b, int scale) {
        return b.add(BigDecimal.ZERO).setScale(scale, BigDecimal.ROUND_UP);
    }

    /**
     * 
     * p2d:科学计数法转常规数字显示 @param val 数字格式化 @param scale 指定转换位数 @return @throws
     */
    public static BigDecimal p2d(BigDecimal val) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(CAL_PRECISION);
        return MathHelper.d2b(nf.format(val));
    }

    /**
     * 
     * p2d:科学计数法转常规数字显示 @param val 数字格式化 @param scale 指定转换位数 @return @throws
     */
    public static BigDecimal p2d(BigDecimal val, int scale) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(CAL_PRECISION);
        return subBigDecimalDown(MathHelper.d2b(nf.format(val)), scale);
    }

    /**
     * 
     * p2d:科学计数法转常规数字显示(多余+1) @param val 数字格式化 @param scale
     * 指定转换位数 @return @throws
     */
    public static BigDecimal p2dup(BigDecimal val, int scale) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(CAL_PRECISION);
        return subBigDecimalUp(MathHelper.d2b(nf.format(val)), scale);
    }

    public static BigDecimal p2d(String val) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(CAL_PRECISION);
        return MathHelper.d2b(nf.format(val));
    }

    public static BigDecimal p2d(double val) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(CAL_PRECISION);
        return MathHelper.d2b(nf.format(val));
    }

    /**
     * double转换成BigDecimal封装方法
     * 
     * @param value 需要转换的double数字
     * 
     * @return 转换后的数字
     * 
     * @throws
     */
    public static BigDecimal d2b(double value) {
        return new BigDecimal(String.valueOf(value));
    }

    public static BigDecimal d2b(String value) {
        return new BigDecimal(value);
    }

    /**
     * 加法运算
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @param scale
     *            精度
     * @return
     */
    public static BigDecimal add(BigDecimal v1, BigDecimal v2, int scale) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 加法运算
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @param scale
     *            精度
     * @return
     */
    public static BigDecimal add(String v1, String v2, int scale) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 减法运算
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @param scale
     * @return
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2, int scale) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 减法运算
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @param scale
     * @return
     */
    public static BigDecimal sub(String v1, String v2, int scale) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 乘法运算
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @param scale
     * @return
     */
    public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int scale) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 乘法运算
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @param scale
     * @return
     */
    public static BigDecimal mul(String v1, String v2, int scale) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 除法运算
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (v2.doubleValue() < 0)
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 除法运算
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     * @return
     */
    public static BigDecimal div(String v1, String v2, int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        if (b2.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        return b1.divide(b2, scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 四舍五入
     * 
     * @param v
     * @param scale
     * @return
     */
    public static BigDecimal round(double v, int scale) {
        if (scale < 0)
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        BigDecimal b1 = new BigDecimal(String.valueOf(v));
        BigDecimal b2 = new BigDecimal("1");
        return b1.divide(b2, scale, BigDecimal.ROUND_FLOOR);
    }

    /**
     * 精确小数位
     * 
     * @param v
     * @param scale
     * @return
     */
    public static double places(double v, int scale) {
        if (scale < 0)
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        BigDecimal b1 = new BigDecimal(String.valueOf(v));
        BigDecimal b2 = b1.setScale(scale, b1.ROUND_DOWN);
        return b2.doubleValue();
    }

    /**
     * 转换Float
     * 
     * @param v
     * @return
     */
    public static float convertsToFloat(double v) {
        BigDecimal b = new BigDecimal(String.valueOf(v));
        return b.floatValue();
    }

    /**
     * 转换Int
     * 
     * @param v
     * @return
     */
    public static int convertsToInt(double v) {
        BigDecimal b = new BigDecimal(String.valueOf(v));
        return b.intValue();
    }

    /**
     * 转换Long
     * 
     * @param v
     * @return
     */
    public static long convertsToLong(double v) {
        BigDecimal b = new BigDecimal(String.valueOf(v));
        return b.longValue();
    }

    /**
     * 返回大值
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static double returnMax(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.max(b2).doubleValue();
    }

    /**
     * 返回小值
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static double returnMin(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.min(b2).doubleValue();
    }

    /**
     * 对比数字
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static int compareTo(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.compareTo(b2);
    }

}
