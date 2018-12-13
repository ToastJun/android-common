package com.toast.common.designMode.factory.abstractfac.model;

public abstract class BlackHuman implements Human {
    @Override
    public void getColor() {
        System.out.print("黑色");
    }

    @Override
    public void getCountry() {
        System.out.print("非洲");
    }
}
