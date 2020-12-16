package org.lg.pay.module.controller.designpattern.observer;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * @ClassName BellEventTest
 * @Deacription TODO观察者模式--铃声时间处理测试
 * @Author zlg
 * @Date 2020/3/6 10:44
 * @Version 1.0
 **/
public class BellEventTest {
    public static void main(String[] args) {

        BellEventListener tech = new TeachEventListener();//注册监听器（老师）
        BellEventListener stu = new StuEventListener();//注册监听器（学生）
        BellEventSource bell = new BellEventSource();//铃（事件源）
        bell.addPersonListener(tech);
        bell.addPersonListener(stu);
        //打上课铃声
        bell.ring(true);
        System.out.println("----------------");
        //打下课铃
        bell.ring(false);
    }
}

//铃声事件类：用于封装事件源及一些与事件相关的参数
class RingEvent extends EventObject {
    //true标识上课铃声，false标识下课铃声
    private boolean sound;

    public boolean isSound() {
        return sound;
    }

    public RingEvent setSound(boolean sound) {
        this.sound = sound;
        return this;
    }

    public RingEvent(Object source, boolean sound) {
        super(source);
        this.sound = sound;
    }
}

//抽象观察者类：铃声事件监听器
interface BellEventListener extends EventListener {
    //事件处理方法，听到铃声
    void heardBell(RingEvent e);
}

//具体观察者类：老师事件监听器
class TeachEventListener implements BellEventListener {

    @Override
    public void heardBell(RingEvent e) {
        if (e.isSound()) {
            System.out.println("老师上课了。。。");
        } else {
            System.out.println("老师下课了。。。");
        }
    }
}

//具体观察者类：学生事件监听器
class StuEventListener implements BellEventListener {

    @Override
    public void heardBell(RingEvent e) {
        if (e.isSound()) {
            System.out.println("同学们，上课了。。。");
        } else {
            System.out.println("同学们，下课了。。。");
        }

    }
}

//目标类：事件源，铃
class BellEventSource {
    private List<BellEventListener> listeners = new ArrayList<>();

    //给事件源绑定监听器
    public void addPersonListener(BellEventListener ren) {
        listeners.add(ren);
    }

    //事件触发器：敲钟，当铃声sound的值发生变化时，触发事件。
    public void ring(boolean sound) {
        String type = sound ? "上课铃" : "下课铃";
        System.out.println(type + "响起！！！");
        RingEvent event = new RingEvent(this,sound);
        //通知注册在该事件源上的所有监听器
        notifies(event);
    }

    //当事件发生时，通知绑定在该事件源上的所有监听器做出反应（调用事件处理方法）
    protected void notifies(RingEvent e) {
        for (BellEventListener listener : listeners) {
            listener.heardBell(e);
        }
    }
}