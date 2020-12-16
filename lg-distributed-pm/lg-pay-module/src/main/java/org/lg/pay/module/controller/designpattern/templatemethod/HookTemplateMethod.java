package org.lg.pay.module.controller.designpattern.templatemethod;

public class HookTemplateMethod {
    public static void main(String[] args) {
        HookAbstractClass tm = new HookConcreteClass();
        tm.templateMethod();
    }
}

// 含钩子方法的抽象类
abstract class HookAbstractClass {
    public void templateMethod() {
        abstractMethod1();
        hookMethod1();
        if (hookMethod2()) {
            specificMethod();
        }
        abstractMethod2();
    }

    abstract void abstractMethod1();// 抽象方法1

    abstract void abstractMethod2();// 抽象方法2

    void hookMethod1() {
    }// 钩子方法1

    boolean hookMethod2() {// 钩子方法2
        return true;
    }

    void specificMethod() {// 具体方法
        System.out.println("抽象类中的具体方法被调用...");
    }
}

// 含钩子方法的具体子类
class HookConcreteClass extends HookAbstractClass {

    @Override
    void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    @Override
    void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }

    void hookMethod1() {
        System.out.println("钩子方法1被重写...");
    }

    boolean hookMethod2() {
        return false;
    }

}
