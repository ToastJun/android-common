package com.toast.common.designMode.factory.abstractfac.test;

import com.toast.common.designMode.factory.abstractfac.FemaleHumanFactory;
import com.toast.common.designMode.factory.abstractfac.HumanFactory;
import com.toast.common.designMode.factory.abstractfac.MaleHumanFactory;
import com.toast.common.designMode.factory.abstractfac.model.Human;

public class Test {

    public static void main(String[] args) {
        HumanFactory maleHumanFactory = new MaleHumanFactory();
        HumanFactory femaleHumanFactory = new FemaleHumanFactory();

        Human maleYellow = maleHumanFactory.createYellowHuman();
        Human femaleYellow = femaleHumanFactory.createYellowHuman();

        Human maleBlack = maleHumanFactory.createBlackHuman();
        Human femaleBlack = femaleHumanFactory.createBlackHuman();

        maleYellow.getColor();
        maleYellow.getGender();

        System.out.println();

        femaleBlack.getColor();
        femaleBlack.getGender();
    }
}
