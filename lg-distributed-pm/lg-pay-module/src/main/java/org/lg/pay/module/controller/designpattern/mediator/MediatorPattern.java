package org.lg.pay.module.controller.designpattern.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MediatorPattern
 * @Deacription TODO  中介者模式
 * @Author zlg
 * @Date 2020/3/6 15:56
 * @Version 1.0
 **/
public class MediatorPattern {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        ConcreteColleague1 concreteColleague1 = new ConcreteColleague1();
        ConcreteColleague2 concreteColleague2 = new ConcreteColleague2();
        mediator.register(concreteColleague1);
        mediator.register(concreteColleague2);
        concreteColleague1.send();
        System.out.println("------------------------");
        concreteColleague2.send();
    }
}

//抽象中介者
abstract class Mediator {
    abstract void register(Colleague colleague);

    abstract void relay(Colleague cl);//转发
}

//具体中介者
class ConcreteMediator extends Mediator {
    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    void relay(Colleague cl) {
        for (Colleague colleague : colleagues) {
            if (!colleague.equals(cl)) {
                colleague.receive();
            }
        }

    }
}

//抽象同事类
abstract class Colleague {
    protected Mediator mediator;

    public Colleague setMediator(Mediator mediator) {
        this.mediator = mediator;
        return this;
    }

    abstract void receive();

    abstract void send();
}

//具体同事类1
class ConcreteColleague1 extends Colleague {

    @Override
    void receive() {
        System.out.println("具体同事类1收到请求。");
    }

    @Override
    void send() {
        System.out.println("具体同事类1发出请求。");
        mediator.relay(this);//请中介者转发
    }
}

//具体同事类2
class ConcreteColleague2 extends Colleague {

    @Override
    void receive() {
        System.out.println("具体同事类2收到请求。");
    }

    @Override
    void send() {
        System.out.println("具体同事类2发出请求。");
        mediator.relay(this);//请中介者转发
    }
}

