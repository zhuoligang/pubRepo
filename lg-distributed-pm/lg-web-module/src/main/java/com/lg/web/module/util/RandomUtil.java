package com.lg.web.module.util;

import java.util.Random;

public class RandomUtil {
	
    //生成min->max之间的数,最小生成的随机数为min，最大生成的随机数为max  
    public static int getRandomNum(int min,int max){  
         Random rdm = new Random();  
         return rdm.nextInt(max-min+1)+min;  
    } 
    
    //生成min->max之间的数,最小生成的随机数为min，最大生成的随机数为max  
    public static double getRandomNum2(int min,int max){  
        min = min + 1;
        max = max - 1;
        return Math.round(Math.random()*(max-min))+min;
    }
    
    public static double getRandomNum3(long min,long max){  
        min = min + 1;
        max = max - 1;
        return Math.round(Math.random()*(max-min))+min;
    }
}
