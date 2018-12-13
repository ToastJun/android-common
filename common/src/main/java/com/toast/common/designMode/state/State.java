package com.toast.common.designMode.state;

/**
 * 不同状态下的行为
 */
public interface State {
    // 投入25分
    void insertQuarter();
    // 退回25分
    void ejectQuarter();
    // 转动曲柄
    void turnCrank();
    // 发放糖果
    void dispense();
}
