package com.toast.common.designMode.state;

public class NoQuarterState implements State{

    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("你投入了25分钱");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你还没有投入过25分钱");
    }

    @Override
    public void turnCrank() {
        System.out.println("你转动了曲柄，但里面并没有钱");
    }

    @Override
    public void dispense() {
        System.out.println("你需要先投币");
    }
}
