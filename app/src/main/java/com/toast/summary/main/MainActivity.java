package com.toast.summary.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.toast.app.wanandroid.data.ARouterUrl;
import com.toast.common.utils.AppUtils;
import com.toast.core.base.WBaseActivity;
import com.toast.core.base.WBasePresenter;
import com.toast.summary.R;
import com.toast.summary.bean.MainAppBean;
import com.toast.summary.main.adapter.MainAdapter;

import java.util.List;

import butterknife.BindView;

@Route(path = ARouterUrl.AR_URL_APP_MAIN)
public class MainActivity extends WBaseActivity<IMainView, MainPresenter<IMainView>>
    implements IMainView, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private MainAdapter mainAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainAdapter = new MainAdapter();
        mainAdapter.setOnItemChildClickListener(this);
        mainAdapter.bindToRecyclerView(rvMain);
        rvMain.setAdapter(mainAdapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void blowUpItemImage(View view) {
        // 对view做放大透明度的动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.main_blowup_alpha);
        view.setAnimation(animation);
        animation.start();
    }

    @Override
    public void onLoadData(List<MainAppBean> mainApps) {
        mainAdapter.setNewData(mainApps);
    }

    @Override
    public void onEmptyData() {

    }

    @Override
    public void onLoadError(String msg) {

    }

    @Override
    public void onNetError(String msg) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.stv_content:
                // 点击放大图片
                blowUpItemImage(adapter.getViewByPosition(position, R.id.riv_item));
                break;
        }
    }
}
