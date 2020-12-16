package org.lg.pay.module.controller.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName VisitorPattern
 * @Deacription TODO 访问者模式
 * @Author zlg
 * @Date 2020/3/9 11:56
 * @Version 1.0
 **/
public class VisitorPattern {
    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor  = new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("---------------------------------");
        visitor = new ConcreteVisitorB();
        os.accept(visitor);
    }
}

//抽象访问者
interface Visitor {
    void visit(ConcreteElementA concreteElementA);

    void visit(ConcreteElementB concreteElementB);
}

//具体访问者A
class ConcreteVisitorA implements Visitor {

    @Override
    public void visit(ConcreteElementA concreteElementA) {
        System.out.println("具体访问者A访问-->" + concreteElementA.operationA());
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        System.out.println("具体访问者A访问-->" + concreteElementB.operationB());
    }
}

//具体访问者B
class ConcreteVisitorB implements Visitor {

    @Override
    public void visit(ConcreteElementA concreteElementA) {
        System.out.println("具体访问者B访问-->" + concreteElementA.operationA());
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        System.out.println("具体访问者B访问-->" + concreteElementB.operationB());
    }
}

//抽象元素类
interface Element {
    void accept(Visitor visitor);
}

//具体元素类A
class ConcreteElementA implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "具体元素A的操作。";
    }
}

//具体元素类B
class ConcreteElementB implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "具体元素B的操作。";
    }
}

//对象结构角色
class ObjectStructure {
    private List<Element> list = new ArrayList<>();

    public void accept(Visitor visitor) {
        for (Element element : list) {
            element.accept(visitor);
        }
    }

    public void add(Element element) {
        list.add(element);
    }

    public void remove(Element element) {
        list.remove(element);
    }

}