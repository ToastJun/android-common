package com.toast.common.designMode.state;

public class Test {

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(1);
        gumballMachine.dispense();
        gumballMachine.ejectQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.dispense();
    }

}
