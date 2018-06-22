package com.toast.summary.main.adapter;

import android.support.annotation.Nullable;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.toast.summary.R;
import com.toast.summary.bean.MainAppBean;
import com.toast.widget.supertext.SuperText;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<MainAppBean, BaseViewHolder>{

    public MainAdapter() {
        super(R.layout.rv_item_main_app);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainAppBean item) {
        ((SuperTextView) helper.getView(R.id.stv_content)).setCenterString(item.getAppName());
        helper.addOnClickListener(R.id.stv_content);
        helper.addOnClickListener(R.id.riv_item);
    }
}
