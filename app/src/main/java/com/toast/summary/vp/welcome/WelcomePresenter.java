package com.toast.summary.vp.welcome;

import com.toast.common.utils.HandlerUtil;
import com.toast.core.base.WBasePresenter;

public class WelcomePresenter extends WBasePresenter<IWelcomeView>{
    @Override
    protected void start() {
        HandlerUtil.postDelay(new Runnable() {
            @Override
            public void run() {
                getView().toMainActivity();
            }
        }, 1500);
    }
}
