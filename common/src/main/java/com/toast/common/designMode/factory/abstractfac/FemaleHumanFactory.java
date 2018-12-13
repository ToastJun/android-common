package com.toast.common.designMode.factory.abstractfac;

import com.toast.common.designMode.factory.abstractfac.model.BlackFemaleHuman;
import com.toast.common.designMode.factory.abstractfac.model.Human;
import com.toast.common.designMode.factory.abstractfac.model.YellowFemaleHuman;

public class FemaleHumanFactory implements HumanFactory {
    @Override
    public Human createYellowHuman() {
        return new YellowFemaleHuman();
    }

    @Override
    public Human createBlackHuman() {
        return new BlackFemaleHuman();
    }
}
