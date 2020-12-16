package org.lg.pay.module.controller.designpattern.command;

// 抽象命令
interface Command {
    abstract void execute();
}

//命令模式
public class CommandPattern {
    public static void main(String[] args) {
        Command cmd = new ConcreteCommand();
        Invoker ir = new Invoker(cmd);
        System.out.println("客户访问调用者的call()方法...");
        ir.call();
    }
}

// 具体命令
class ConcreteCommand implements Command {
    private Receiver receiver = new Receiver();

    @Override
    public void execute() {
        this.receiver.action();
    }

}

// 调用者
class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call() {
        System.out.println("调用者执行命令command...");
        command.execute();
    }
}

// 接收者
class Receiver {
    public void action() {
        System.out.println("接收者action（）方法被调用...");
    }
}