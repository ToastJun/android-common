package com.toast.common.designMode.state;

public class HasQuarterState implements State{

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("你不能再投入其他的25分钱");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("退回25分钱");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("你转动了曲柄");
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("没有糖果发放");
    }
}
