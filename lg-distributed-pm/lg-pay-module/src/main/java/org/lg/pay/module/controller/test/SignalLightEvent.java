package org.lg.pay.module.controller.test;

import java.util.*;


/**
 * @Description 交通信号灯事件处理程序
 * @ClassName SignalLightEvent
 * @Author zlg
 * @date 2020.03.26 16:30
 */
public class SignalLightEvent {
    public static void main(String[] args) {
        SignalLight sl = new SignalLight();
        Vehicle v1 = new Car();
        Vehicle v2 = new Buses();
        sl.addVehicleListener(v1);
        sl.addVehicleListener(v2);
        sl.changeColor("红色");
    }
}

//信号灯颜色
class SignalColor extends EventObject {
    //红色和绿色
    private String color;

    public SignalColor(Object source, String color) {
        super(source);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public SignalColor setColor(String color) {
        this.color = color;
        return this;
    }
}

//目标类：事件源，交通信号灯
class SignalLight {
    //监听器容器
    private List<Vehicle> listener;

    SignalLight() {
        listener = new ArrayList<>();
    }
    //给事件源绑定监听器
    public void addVehicleListener(Vehicle vehicle){
        listener.add(vehicle);
    }
    //当事件发生时，通知绑定在该事件源上的所有监听器做出反应（调用事件处理方法）
    protected void notifies(SignalColor e){
        for (Vehicle vehicle : listener) {
            vehicle.see(e);
        }
    }
    //事件触发器：信号灯改变颜色
    public void changeColor(String color){
        System.out.println(color + "信号灯亮了。。。");
        SignalColor event = new SignalColor(this,color);
        notifies(event);
    }

}

//抽象观察者类：车
interface Vehicle extends EventListener {
    //时间处理方法，看见
    public void see(SignalColor e);
}

//具体观察者类：轿车
class Car implements Vehicle {

    @Override
    public void see(SignalColor e) {
        if ("红色".equals(e.getColor())) {
            System.out.println("红灯亮，轿车停！");
        } else {
            System.out.println("绿灯亮，轿车行！");
        }
    }
}

//具体观察者类：公交车
class Buses implements Vehicle {

    @Override
    public void see(SignalColor e) {
        if ("红色".equals(e.getColor())) {
            System.out.println("红灯亮，公交车停！");
        } else {
            System.out.println("绿灯亮，公交车行！");
        }
    }
}