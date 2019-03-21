package com.bw.xiangmu.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.xiangmu.Bean.ShowBean;
import com.bw.xiangmu.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @Auther: len
 * @Date: 2019/2/28 09:32:25
 * @Description:
 */
public class Mlssadapter extends BaseQuickAdapter<ShowBean.ResultBean.MlssaBean.CommodityListBeanXX, BaseViewHolder> {
    public Mlssadapter(int layoutResId, @Nullable List<ShowBean.ResultBean.MlssaBean.CommodityListBeanXX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShowBean.ResultBean.MlssaBean.CommodityListBeanXX item) {
//赋值
        helper.setText(R.id.name, item.getCommodityName());
        helper.setText(R.id.price, "$"+item.getPrice()+".00");
        ImageView image = helper.getView(R.id.tu);
        Glide.with(mContext).load(item.getMasterPic()).into(image);
    }
}
