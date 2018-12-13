package com.toast.common.designMode.state;

public class SoldState implements State{

    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("请稍等，正在为你发放糖果");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("抱歉，你已经转动了曲柄，正在为你发放糖果，无法退回钱币");
    }

    @Override
    public void turnCrank() {
        System.out.println("现在正在为你发放糖果，再次转动无法得到更多糖果");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("糖果卖完啦！");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}
