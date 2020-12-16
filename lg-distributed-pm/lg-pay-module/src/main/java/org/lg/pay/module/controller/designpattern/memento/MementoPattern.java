package org.lg.pay.module.controller.designpattern.memento;

/**
 * @ClassName MementoPattern
 * @Deacription TODO 备忘录模式（快照模式）
 * @Author zlg
 * @Date 2020/3/10 15:28
 * @Version 1.0
 **/
public class MementoPattern {
    public static void main(String[] args) {
        Originator or = new Originator();
        or.setState("S0");
        System.out.println("初始状态：" + or.getState());

        Caretaker cr = new Caretaker();
        cr.setMemento(or.createMemento());//保存初始状态

        or.setState("S1");
        System.out.println("新的状态：" + or.getState());

        or.restoreMemento(cr.getMemento());//恢复状态
        System.out.println("恢复状态：" + or.getState());
    }
}

//备忘录
class Memento {
    private String state;

    Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento setState(String state) {
        this.state = state;
        return this;
    }
}

//发起人
class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public Originator setState(String state) {
        this.state = state;
        return this;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(Memento memento) {
        setState(memento.getState());
    }
}

//管理者
class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public Caretaker setMemento(Memento memento) {
        this.memento = memento;
        return this;
    }
}