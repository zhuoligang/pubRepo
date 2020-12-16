package org.lg.pay.module.controller.designpattern.interpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * 抽象表达式类
 */
interface Expression {
    public boolean interpret(String info);
}

/**
 * @Description 解释器模式
 * @ClassName InterpreterPatternDemo
 * @Author zlg
 * @date 2020.03.23 13:53
 */
public class InterpreterPatternDemo {
    public static void main(String[] args) {
        Context context = new Context();
        context.freeRide("成都的儿童");
        context.freeRide("都江堰的妇女");
    }
}

/**
 * 终结符表达式类
 */
class TerminalExpression implements Expression {
    private Set<String> set = new HashSet<>();

    public TerminalExpression(String[] data) {
        for (int i = 0; i < data.length; i++) {
            set.add(data[i]);
        }
    }

    @Override
    public boolean interpret(String info) {
        if (set.contains(info)) {
            return true;
        }
        return false;
    }
}

/**
 * 非终结符表达式类
 */
class AndExpression implements Expression {
    private Expression city = null, person = null;

    AndExpression(Expression city, Expression person) {
        this.city = city;
        this.person = person;
    }

    @Override
    public boolean interpret(String info) {
        String[] s = info.split("的");
        return city.interpret(s[0]) && person.interpret(s[1]);
    }
}

/**
 * 环境类
 */
class Context {
    private String[] citys = {"成都", "资阳"};
    private String[] persons = {"老人", "妇女", "儿童"};
    private Expression cityPerson;

    public Context() {
        Expression city = new TerminalExpression(citys);
        Expression person = new TerminalExpression(persons);
        cityPerson = new AndExpression(city, person);
    }

    public void freeRide(String info) {
        boolean ok = cityPerson.interpret(info);
        if (ok) {
            System.out.println("您是" + info + ",您本次乘车免费！");
        } else {
            System.out.println(info + ",您不是免费人员，本次乘车扣费2元！");
        }
    }
}