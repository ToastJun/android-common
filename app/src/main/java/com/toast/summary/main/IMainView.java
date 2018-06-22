package com.toast.summary.main;

import android.view.View;

import com.toast.core.base.WIBaseView;
import com.toast.summary.bean.MainAppBean;

import java.util.List;

public interface IMainView extends WIBaseView<List<MainAppBean>> {
    void blowUpItemImage(View view);
}
