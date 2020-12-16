package org.lg.pay.module.controller.designpattern.memento;

/**
 * @Description 备忘录（快照）模式混合原型模式
 * @ClassName PrototypeMemento
 * @Author zlg
 * @date 2020.03.21 16:16
 */
public class PrototypeMemento {
    public static void main(String[] args) {
        OriginatorPrototype opt = new OriginatorPrototype();
        opt.setState("1");
        System.out.println("原始状态："+opt.getState());
        PrototypeCaretaker pc = new PrototypeCaretaker();
        OriginatorPrototype memento = opt.createMemento();
        //保存状态
        pc.setMemento(memento);
        opt.setState("2");
        System.out.println("修改后的状态："+opt.getState());
        //还原状态
        opt.restoreMemento(pc.getMemento());
        System.out.println("还原后的状态："+opt.getState());
    }
}

/**
 * 发起人原型
 */
class OriginatorPrototype implements Cloneable {
    private String state;

    public String getState() {
        return state;
    }

    public OriginatorPrototype setState(String state) {
        this.state = state;
        return this;
    }

    public OriginatorPrototype createMemento() {
        try {
            return (OriginatorPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void restoreMemento(OriginatorPrototype opt) {
        this.setState(opt.getState());
    }

//    public OriginatorPrototype clone() {
//        try {
//            return (OriginatorPrototype) super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}

/**
 * 原型管理者
 */
class PrototypeCaretaker {
    private OriginatorPrototype opt;

    public void setMemento(OriginatorPrototype opt) {
        this.opt = opt;
    }

    public OriginatorPrototype getMemento() {
        return this.opt;
    }
}