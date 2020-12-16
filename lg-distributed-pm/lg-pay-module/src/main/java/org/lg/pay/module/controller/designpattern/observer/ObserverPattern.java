package org.lg.pay.module.controller.designpattern.observer;

import java.awt.peer.CanvasPeer;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ObserverPattern
 * @Deacription TODO 观察者模式
 * @Author zlg
 * @Date 2020/3/5 14:38
 * @Version 1.0
 **/
public class ObserverPattern {
    public static void main(String[] args) {
        Subject sub = new ConcreteSubject();
        ConcreteObserver1 ob1 = new ConcreteObserver1();
        ConcreteObserver2 ob2 = new ConcreteObserver2();
        sub.add(ob1);
        sub.add(ob2);
        sub.notifyObserver();
    }
}

//抽象目标
abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    //增加观察者（订阅）
    public void add(Observer observer) {
        observers.add(observer);
    }

    //删除观察者（取消订阅）
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    //通知观察者方法
    abstract void notifyObserver();
}

//具体目标
class ConcreteSubject extends Subject {

    @Override
    void notifyObserver() {
        System.out.println("具体目标发生改变。。。");
        System.out.println("----------------------");
        for (Observer observer : observers) {
            observer.response();
        }
    }
}

//抽象观察者
interface Observer {
    void response();//反应
}

//具体观察者1
class ConcreteObserver1 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者1做出反应！");
    }
}

//具体观察者2
class ConcreteObserver2 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者2做出反应！");
    }
}