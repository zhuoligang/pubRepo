package org.lg.pay.module.controller.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IteratorPattern
 * @Deacription TODO 迭代器模式
 * @Author zlg
 * @Date 2020/3/7 15:36
 * @Version 1.0
 **/
public class IteratorPattern {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("北京大学");
        aggregate.add("清华大学");
        aggregate.add("浙江大学");
        Iterator iterator = aggregate.getIterator();
        System.out.print("聚合内容有：");
        while (iterator.hasNext()){
            Object o = iterator.next();
            System.out.print(o + "\t");
        }
        Object first = iterator.first();
        System.out.println("\nfirst:"+ first);

    }
}

//抽象聚合
interface Aggregate{
    void add(Object obj);
    void remove(Object obj);
    Iterator getIterator();
}

//具体聚合
class ConcreteAggregate implements Aggregate{
    private List<Object> list = new ArrayList<>();
    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(list);
    }
}

//抽象迭代器
interface Iterator{
    Object first();
    Object next();
    boolean hasNext();
}

//具体迭代器
class ConcreteIterator implements Iterator{
    private List<Object> list = null;
    private int index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object first() {
        index = 0;
        Object obj = list.get(index);
        return obj;
    }

    @Override
    public Object next() {
        Object obj = null;
        if (hasNext()){
            obj = list.get(++index);
        }
        return obj;
    }

    @Override
    public boolean hasNext() {
        if (index < list.size() -1){
            return true;
        }
        return false;
    }
}