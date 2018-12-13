package com.toast.common.designMode.state;

/**
 * 糖果机
 */
public class GumballMachine {
    // 糖果机的状态-不同的状态下相同的操作会有不同的结果
    private State state;

    private State hasQuarterState;

    private State noQuarterState;

    private State soldState;

    private State soldOutState;
    // 糖果机中糖果的个数
    private int count = 0;

    public GumballMachine(int numberGumballs) {
        count = numberGumballs;
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);

        if (numberGumballs > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
    }

    public void releaseBall() {
        System.out.println("糖果已经发放完成！");
        if (count > 0) {
            count--;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
    }

    public void dispense() {
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public int getCount() {
        return count;
    }

}
