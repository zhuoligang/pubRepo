package org.lg.pay.module.controller.designpattern.interpreter;


import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;

/**
 * @Description Java expression parser 的简称，即 Java 表达式分析器
 * @ClassName JepDemo
 * @Author zlg
 * @date 2020.03.23 15:21
 */
public class JepDemo {
    public static void main(String[] args) throws JepException {
        Jep jep = new Jep();
        //定义要计算的数据表达式
        String result = "本金*利率*时间";
        //赋值
        jep.addVariable("本金", 10000);
        jep.addVariable("利率", 0.038);
        jep.addVariable("时间", 2);
        jep.parse(result);
        //计算
        Object evaluate = jep.evaluate();
        System.out.println("存款利息：" + evaluate);

    }
}