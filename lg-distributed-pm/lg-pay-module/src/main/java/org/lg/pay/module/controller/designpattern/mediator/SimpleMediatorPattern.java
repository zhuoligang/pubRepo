package org.lg.pay.module.controller.designpattern.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SimpleMediatorPattern
 * @Deacription TODO 简化中介者模式
 * @Author zlg
 * @Date 2020/3/7 13:39
 * @Version 1.0
 **/
public class SimpleMediatorPattern {
    public static void main(String[] args) {
        SimpleColleague s1,s2;
        s1 = new SimpleConcreteColleague1();
        s2 = new SimpleConcreteColleague2();
        s1.send();
        System.out.println("----------------------------------");
        s2.send();
    }
}

//简单单例中介者
class SimpleMediator {
    private static SimpleMediator smd = new SimpleMediator();
    private List<SimpleColleague> colleagues = new ArrayList<>();

    private SimpleMediator() {
    }

    public static SimpleMediator getMediator() {
        return smd;
    }

    public void register(SimpleColleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
        }
    }

    public void relay(SimpleColleague sc) {
        for (SimpleColleague colleague : colleagues) {
            if (!colleague.equals(sc)) {
                colleague.receive();
            }
        }

    }
}

//抽象同事类
interface SimpleColleague {
    void receive();

    void send();
}

//具体同事类1
class SimpleConcreteColleague1 implements SimpleColleague {
    private SimpleMediator smd = SimpleMediator.getMediator();

    SimpleConcreteColleague1() {
        smd.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类1：收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类1：发出请求。。。");
        smd.relay(this);
    }
}

//具体同事类2
class SimpleConcreteColleague2 implements SimpleColleague{
    private SimpleMediator smd = SimpleMediator.getMediator();

    public SimpleConcreteColleague2() {
        smd.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类2：收到请求。");
    }

    @Override
    public void send() {
        System.out.println("具体同事类2：发出请求。。。");
        smd.relay(this);
    }
}