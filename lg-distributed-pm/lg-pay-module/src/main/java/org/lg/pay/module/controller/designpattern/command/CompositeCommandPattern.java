package org.lg.pay.module.controller.designpattern.command;

import java.util.ArrayList;

// 抽象命令
interface AbstractCommand {
    abstract void execute();
}

//组合命令模式
public class CompositeCommandPattern {
    public static void main(String[] args) {
        AbstractCommand cmd1 = new ConcreteCommand1();
        AbstractCommand cmd2 = new ConcreteCommand2();
        CompositeInvoker ci = new CompositeInvoker();
        ci.add(cmd1);
        ci.add(cmd2);
        System.out.println("客户访问调用者的execute()方法。。。");
        ci.execute();
    }
}

// 树叶构件:具体命令1
class ConcreteCommand1 implements AbstractCommand {

    private CompositeReceiver receiver = new CompositeReceiver();

    @Override
    public void execute() {
        this.receiver.action1();
    }
}

// 树叶构件:具体命令2
class ConcreteCommand2 implements AbstractCommand {

    private CompositeReceiver receiver = new CompositeReceiver();

    @Override
    public void execute() {
        this.receiver.action2();
    }
}

// 接收者
class CompositeReceiver {
    public void action1() {
        System.out.println("接收者action1（）方法被调用。。。");
    }

    public void action2() {
        System.out.println("接收者action2（）方法被调用。。。");
    }
}

// 树枝构件：调用者
class CompositeInvoker implements AbstractCommand {
    private ArrayList<AbstractCommand> children = new ArrayList<>();

    public void add(AbstractCommand c) {
        children.add(c);
    }

    public void remove(AbstractCommand c) {
        children.remove(c);
    }

    public AbstractCommand getChild(int i) {
        return children.get(i);
    }

    @Override
    public void execute() {
        for (AbstractCommand abstractCommand : children) {
            abstractCommand.execute();
        }

    }
}
