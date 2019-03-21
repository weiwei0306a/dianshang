package com.bw.xiangmu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.xiangmu.Adapter.Mlssadapter;
import com.bw.xiangmu.Adapter.Pzshadapter;
import com.bw.xiangmu.Adapter.Rxxpadapter;
import com.bw.xiangmu.Bean.BanBean;
import com.bw.xiangmu.Bean.ShowBean;
import com.bw.xiangmu.Contract.Ban_Contract;
import com.bw.xiangmu.Persenter.Ban_persenter;
import com.bw.xiangmu.R;
import com.bw.xiangmu.activity.Search;
import com.bw.xiangmu.activity.details;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

public class Home extends Fragment implements Ban_Contract.Contract_View {

    private MZBannerView fly;
    private RecyclerView rlv, rlv1, rlv2;
    private EditText edtext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, null, false);
        fly = view.findViewById(R.id.fly);
        rlv = view.findViewById(R.id.shou1);
        rlv1 = view.findViewById(R.id.shou2);
        rlv2 = view.findViewById(R.id.shou3);
        edtext = view.findViewById(R.id.shu);

        Ban_persenter persenter = new Ban_persenter();
        persenter.attachView(this);
        persenter.requestData();
        persenter.ShowData();
        //输入框监听
        edtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Search.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void Show_View(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                ShowBean showBean = gson.fromJson(message, ShowBean.class);
                //===============================热销产品===============================
                final List<ShowBean.ResultBean.RxxpBean.CommodityListBean> rxx = showBean.getResult().getRxxp().getCommodityList();
                LinearLayoutManager shaManager = new GridLayoutManager(getActivity(), 3);
                rlv.setLayoutManager(shaManager);
                Rxxpadapter sha1dapter = new Rxxpadapter(R.layout.mlss_item, rxx);
                rlv.setAdapter(sha1dapter);
                rlv.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int id = rxx.get(position).getCommodityId();
                        Intent intent = new Intent(getActivity(), details.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });

                //===============================魔力时尚===============================
                final List<ShowBean.ResultBean.MlssaBean.CommodityListBeanXX> mlss_list = showBean.getResult().getMlss().getCommodityList();
                //Reyscview
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                rlv1.setLayoutManager(layoutManager);
                Mlssadapter sadapter = new Mlssadapter(R.layout.pzsh_item, mlss_list);
                rlv1.setAdapter(sadapter);
                //点击条目
                rlv1.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int id = mlss_list.get(position).getCommodityId();
                        Intent intent = new Intent(getActivity(), details.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
                //===============================品质生活===============================
                final List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> pzsh_list = showBean.getResult().getPzsh().getCommodityList();
                LinearLayoutManager shManager = new GridLayoutManager(getActivity(), 2);
                rlv2.setLayoutManager(shManager);
                Pzshadapter shadapter = new Pzshadapter(R.layout.mlss_item, pzsh_list);
                rlv2.setAdapter(shadapter);
                //条目点击事件
                rlv2.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int id = pzsh_list.get(position).getCommodityId();
                        Intent intent = new Intent(getActivity(), details.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    //首页轮播图
    @Override
    public void Ban_View(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                BanBean banBean = gson.fromJson(message, BanBean.class);
                List<BanBean.ResultBean> result = banBean.getResult();
                fly.setPages(result, new MZHolderCreator() {
                    @Override
                    public MZViewHolder createViewHolder() {
                        return new BennerViewHolder();
                    }
                });
            }
        });
    }

    //轮播图适配器
    public static class BennerViewHolder implements MZViewHolder<BanBean.ResultBean> {

        private ImageView imageb1;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.ban_item, null);
            imageb1 = (ImageView) view.findViewById(R.id.imageb1);
            return view;
        }

        @Override
        public void onBind(Context context, int i, BanBean.ResultBean resultBean) {
            //glide加载图片
            Glide.with(context).load(resultBean.getImageUrl()).into(imageb1);
        }
    }
}
