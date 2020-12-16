package org.lg.pay.module.controller.designpattern.state;

import java.util.HashMap;

/**
 * @ClassName FlyweightStatePattern
 * @Deacription TODO 共享状态模式
 * @Author zlg
 * @Date 2020/3/5 13:48
 * @Version 1.0
 **/
public class FlyweightStatePattern {
    public static void main(String[] args) {
        ShareContext context = new ShareContext();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }
}

//环境类
class ShareContext{
    private ShareState state;
    private HashMap<String,ShareState> stateSet = new HashMap<>();

    public ShareContext() {
        state = new ConcreteState1();
        stateSet.put("1",state);
        state = new ConcreteState2();
        stateSet.put("2",state);
        state = getState("1");
    }

    //设置新状态
    public ShareContext setState(ShareState state) {
        this.state = state;
        return this;
    }
    //读取状态
    public ShareState getState(String key){
        ShareState s = stateSet.get(key);
        return s;
    }
    //对请求做处理
    public void handle(){
        state.handle(this);
    }
}

//抽象状态类
abstract class ShareState{
    abstract void handle(ShareContext context);
}

//具体状态类1
class ConcreteState1 extends ShareState{

    @Override
    void handle(ShareContext context) {
        System.out.println("当前状态是：状态1");
        context.setState(context.getState("2"));
    }
}

//具体状态类2
class ConcreteState2 extends ShareState{

    @Override
    void handle(ShareContext context) {
        System.out.println("当前状态是：状态2");
        context.setState(context.getState("1"));
    }
}