package org.lg.pay.module.controller.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName CrudeOilFutures
 * @Deacription TODO 观察者模式--原油期货
 * @Author zlg
 * @Date 2020/3/6 13:36
 * @Version 1.0
 **/
public class CrudeOilFutures {
    public static void main(String[] args) {
        OilFutures oil = new OilFutures();
        oil.addObserver(new Bull());
        oil.addObserver(new Bear());
        oil.setPrice(10f);
    }
}

//具体目标类：原油期货
class OilFutures extends Observable {
    private float price;

    public float getPrice() {
        return price;
    }

    public OilFutures setPrice(float price) {
        //设置内部标志位，注明数据发生变化
        super.setChanged();
        //通知观察者价格发生了改变
        super.notifyObservers(price);
        this.price = price;
        return this;
    }
}

//具体观察者类：多方
class Bull implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Float price = ((Float) arg).floatValue();
        if (price > 0) {
            System.out.println("油价上涨" + price + "元，多方高兴了。。。");
        } else {
            System.out.println("油价下跌" + (-price) + "元，多方伤心了。。");
        }

    }
}

//具体观察者类：空方
class Bear implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Float price = ((Float) arg).floatValue();
        if (price > 0) {
            System.out.println("油价上涨" + price + "元，空方伤心了。。。");
        } else {
            System.out.println("油价下跌" + (-price) + "元，空方高兴了。。。");
        }
    }
}