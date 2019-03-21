package com.bw.xiangmu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.xiangmu.Bean.KeyBean;
import com.bw.xiangmu.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @Auther: len
 * @Date: 2019/3/20 20:35:54
 * @Description:
 */
public class Keyadapter extends BaseQuickAdapter<KeyBean.ResultBean, BaseViewHolder> {

    private ImageView img;

    public Keyadapter(int layoutResId, @Nullable List<KeyBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyBean.ResultBean item) {
        helper.setText(R.id.name, item.getCommodityName());
        helper.setText(R.id.price, "￥" + item.getPrice());
        String pic = item.getMasterPic();
        img = helper.getView(R.id.key_img);
        Glide.with(mContext).load(pic).into(img);
    }
}
