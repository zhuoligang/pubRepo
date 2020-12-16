package org.lg.pay.module.controller.designpattern.state;

/**
 * @ClassName ScoreStateTest
 * @Deacription TODO 状态模式测试代码
 * @Author zlg
 * @Date 2020/3/4 17:40
 * @Version 1.0
 **/
public class ScoreStateTest {
    public static void main(String[] args) {
        ScoreContext scoreContext = new ScoreContext();
        System.out.println("学生成绩状态测试：");
        scoreContext.add(30);
        scoreContext.add(40);
        scoreContext.add(-20);
    }
}

//环境类
class ScoreContext {
    private AbstractState state;

    ScoreContext() {
        state = new LowState(this);
    }

    public AbstractState getState() {
        return state;
    }

    public ScoreContext setState(AbstractState state) {
        this.state = state;
        return this;
    }

    public void add(int score) {
        state.addScore(score);
    }

}

//抽象状态类
abstract class AbstractState {
    protected ScoreContext hj;//环境
    protected String stateName;//状态名
    protected int score;

    abstract void checkScore();//检查当前状态

    void addScore(int x) {
        score += x;
        System.out.print("加上：" + x + "分，当前分数：" + score );
        checkScore();
        System.out.println("分,\t当前状态：" + hj.getState().stateName);
    }
}

//具体状态类：不及格
class LowState extends AbstractState {
    public LowState(ScoreContext h) {
        hj = h;
        stateName = "不及格";
        score = 0;
    }

    public LowState(AbstractState state) {
        hj = state.hj;
        stateName = "不及格";
        score = state.score;
    }

    @Override
    void checkScore() {
        if (score >= 90) {
            hj.setState(new HighState(this));
        } else if (score >= 60) {
            hj.setState(new MiddleState(this));
        }
    }
}

//具体状态类：中等
class MiddleState extends AbstractState {
    public MiddleState(AbstractState state) {
        hj = state.hj;
        stateName = "中等";
        score = state.score;
    }

    @Override
    void checkScore() {
        if (score < 60) {
            hj.setState(new LowState(this));
        } else if (score >= 90) {
            hj.setState(new LowState(this));
        }
    }
}

//具体状态类：优秀
class HighState extends AbstractState {
    public HighState(AbstractState state) {
        hj = state.hj;
        stateName = "优秀";
        score = state.score;
    }

    @Override
    void checkScore() {
        if (score < 60) {
            hj.setState(new LowState(this));
        } else if (score < 90) {
            hj.setState(new MiddleState(this));
        }
    }
}