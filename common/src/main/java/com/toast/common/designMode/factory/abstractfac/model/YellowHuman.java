package com.toast.common.designMode.factory.abstractfac.model;

import com.toast.common.designMode.factory.abstractfac.model.Human;

public abstract class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.print("黄颜色");
    }

    @Override
    public void getCountry() {
        System.out.print("亚洲");
    }
}
