package com.toast.common.designMode.factory.abstractfac;

import com.toast.common.designMode.factory.abstractfac.model.Human;

/**
 * 人类抽象工厂类
 */
public interface HumanFactory {

    Human createYellowHuman();

    Human createBlackHuman();

}
