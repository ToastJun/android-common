package com.toast.common.designMode.factory.abstractfac;

import com.toast.common.designMode.factory.abstractfac.model.BlackMaleHuman;
import com.toast.common.designMode.factory.abstractfac.model.Human;
import com.toast.common.designMode.factory.abstractfac.model.YellowMaleHuman;

public class MaleHumanFactory implements HumanFactory {
    @Override
    public Human createYellowHuman() {
        return new YellowMaleHuman();
    }

    @Override
    public Human createBlackHuman() {
        return new BlackMaleHuman();
    }
}
