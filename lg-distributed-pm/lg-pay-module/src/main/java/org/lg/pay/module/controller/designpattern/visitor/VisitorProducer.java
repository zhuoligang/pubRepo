package org.lg.pay.module.controller.designpattern.visitor;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName VisitorProducer
 * @Deacription TODO 访问者模式--利用访问者模式设计艺术公司和造币公司
 * @Author zlg
 * @Date 2020/3/9 14:52
 * @Version 1.0
 **/
public class VisitorProducer {
    public static void main(String[] args) {
        new MaterialWin();
    }
}

//窗体
class MaterialWin extends JFrame implements ItemListener {

    JPanel centerJP;
    String[] select;
    SetMaterial os;//材料集对象
    Company visitor1, visitor2;//访问者对象

    MaterialWin() {
        super("利用访问者模式设计艺术公司和造币公司");
        visitor1 = new ArtCompany();
        visitor2 = new Mint();

        os = new SetMaterial();
        os.add(new Paper());
        os.add(new Cuprum());
        this.setBounds(10, 10, 750, 350);
        this.setResizable(false);
        centerJP = new JPanel();
        this.add("Center", centerJP);

        JRadioButton art, mint;
        art = new JRadioButton("艺术公司", true);
        mint = new JRadioButton("造币公司");
        art.addItemListener(this);
        mint.addItemListener(this);
        ButtonGroup group = new ButtonGroup();
        group.add(art);
        group.add(mint);
        JPanel southJP = new JPanel();
        JLabel yl = new JLabel("原材料有：铜和纸，请选择生产公司：");
        southJP.add(yl);
        southJP.add(art);
        southJP.add(mint);
        this.add("South", southJP);
        select = os.accept(visitor1).split(" ");//获取产品名
        showPicture(select[0], select[1]);
    }

    void showPicture(String cuprum, String paper) {
        centerJP.removeAll();//清除面板内容
        centerJP.repaint();//刷新屏幕
        String fileName1 = "D:\\src\\visitor-picture/" + cuprum + ".jpg";
        String fileName2 = "D:\\src\\visitor-picture/" + paper + ".jpg";
        JLabel lb = new JLabel(new ImageIcon(fileName1), JLabel.CENTER);
        JLabel rb = new JLabel(new ImageIcon(fileName2), JLabel.CENTER);
        centerJP.add(rb);
        centerJP.add(lb);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JRadioButton jc = (JRadioButton) e.getSource();
        if (jc.isSelected()) {
            if (jc.getText().equals("造币公司")) {
                select = os.accept(visitor2).split(" ");
            } else {
                select = os.accept(visitor1).split(" ");
            }
            showPicture(select[0], select[1]);//显示选择的产品
        }
    }
}

//抽象访问者：公司
interface Company {
    String create(Paper element);

    String create(Cuprum element);
}

//具体访问者：艺术公司
class ArtCompany implements Company {

    @Override
    public String create(Paper element) {
        return "讲学图";
    }

    @Override
    public String create(Cuprum element) {
        return "朱熹铜像";
    }
}

//具体访问者：造币公司
class Mint implements Company {

    @Override
    public String create(Paper element) {
        return "纸币";
    }

    @Override
    public String create(Cuprum element) {
        return "铜币";
    }
}

//抽象元素：材料
interface Material {
    String accept(Company visitor);
}

//具体元素：纸
class Paper implements Material {

    @Override
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}

//具体元素：铜
class Cuprum implements Material {

    @Override
    public String accept(Company visitor) {
        return visitor.create(this);
    }
}


//对象结构角色：材料集
class SetMaterial {
    private List<Material> list = new ArrayList<>();

    public String accept(Company visitor) {
        String str = "";
        for (Material material : list) {
            str += material.accept(visitor) + " ";
        }
        //返回某公司的作品集
        return str;
    }

    public void add(Material element) {
        list.add(element);
    }

    public void remove(Material element) {
        list.remove(element);
    }
}

