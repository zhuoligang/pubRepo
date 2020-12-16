package org.lg.pay.module.controller.designpattern.observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RMBrateTest
 * @Deacription TODO 观察者模式--人民币汇率测试
 * @Author zlg
 * @Date 2020/3/5 18:15
 * @Version 1.0
 **/
public class RMBrateTest {
    public static void main(String[] args) {
        Rate rate = new RMBrate();
        Company c1 = new ImportCompany();
        Company c2 = new ExportCompany();
        rate.add(c1);
        rate.add(c2);
        rate.change(10);
        rate.change(-6);
    }
}

//抽象目标：汇率
abstract class Rate {
    protected List<Company> companies = new ArrayList<>();

    //增加观察者
    public void add(Company company) {
        companies.add(company);
    }

    //删除观察者
    public void remove(Company company) {
        companies.remove(company);
    }

    abstract void change(int number);
}

//具体目标：人名币汇率
class RMBrate extends Rate {

    @Override
    void change(int number) {
        for (Company company : companies) {
            company.response(number);
        }
    }
}

//抽象观察者：公司
interface Company {
    void response(int number);
}

//具体观察者1：进口公司
class ImportCompany implements Company {

    @Override
    public void response(int number) {
        if (number > 0) {
            System.out.println("人名币汇率升值" + number + "个基点，降低了进口产品成本，提升了进口公司利润率。");
        } else if (number < 0) {
            System.out.println("人名币汇率贬值" + (-number) + "个基点，提升了进口产品成本，降低了进口公司利润率。");
        }
    }
}

//具体观察者2：出口公司
class ExportCompany implements Company {

    @Override
    public void response(int number) {
        if (number > 0) {
            System.out.println("人名币汇率升值" + number + "个基点，降低了出口产品收入，降低了出口公司销售利润率。");
        } else {
            System.out.println("人名币汇率贬值" + (-number) + "个基点，提升了出口产品收入，提升了出口公司销售利润率。");
        }
    }
}