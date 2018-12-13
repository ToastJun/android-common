package com.toast.common.designMode.state;

public class SoldOutState implements State{

    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("你不能投币，商品已经卖完了");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("不能退款，因为你还没有投币");
    }

    @Override
    public void turnCrank() {
        System.out.println("你转动了曲柄，但是里面并没有糖果");
    }

    @Override
    public void dispense() {
        System.out.println("并没有糖果可以发放");
    }
}
