package com.bw.xiangmu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.xiangmu.Bean.ChildBean;
import com.bw.xiangmu.Contract.DetailsContract;
import com.bw.xiangmu.Persenter.DetailsPersenter;
import com.bw.xiangmu.R;
import com.bw.xiangmu.fragment.Home;
import com.google.gson.Gson;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther: len
 * @Date: 2019/3/19 14:05:40
 * @Description:
 */
public class details extends AppCompatActivity implements DetailsContract.DetailsView {

    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.mai)
    ImageView mai;
    @BindView(R.id.fan)
    ImageView fan;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.x_mei)
    MZBannerView xMei;
    @BindView(R.id.price_date)
    TextView priceDate;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.name1)
    TextView name1;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.web)
    WebView web;
    private DetailsPersenter deta;
    private SharedPreferences sp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        ButterKnife.bind(this);
        //获取首页传过来的id
        final Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        //创建P层
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        deta = new DetailsPersenter();
        deta.attachView(this);
        //取出数据库的userid和sessioid
        sp = getSharedPreferences("user", MODE_PRIVATE);
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", "");
        deta.requestdata(id, userId, sessionId);
        //点击返回事件
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(details.this, Home.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    //更新UI
    @Override
    public void deta_View(final String message) {
        runOnUiThread(new Runnable() {
            private List<String> list;

            @Override
            public void run() {
                Gson gson = new Gson();
                ChildBean childBean = gson.fromJson(message, ChildBean.class);
                ChildBean.ResultBean result = childBean.getResult();
                number.setText("已售" + result.getCommentNum() + "件");
                priceDate.setText("￥" + result.getPrice());
                name1.setText(result.getDescribe() + result.getCommodityName());
                weight.setText("重量" + result.getWeight());
                //webView网页请求
                String details1 = result.getDetails();
                WebSettings settings = web.getSettings();
                settings.setJavaScriptEnabled(true);
                String js = "<script type=\"text/javascript\">" +

                        "var imgs=document.getElementsByTagName('img');" +
                        "for(var i = 0; i<imgs.length; i++){" +
                        "imgs[i].style.width='100%';" +
                        "imgs[i].style.height='auto';" +
                        "}" +
                        "</script>";
                web.loadDataWithBaseURL(null, details1 + js + "<html></body>", "text/html", "utf-8", null);

                //定义一个数组把照片存到数组
                String[] split = result.getPicture().split("\\,");
                //创建一个集合
                list = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    list.add(split[i]);
                }

                xMei.setPages(list, new MZHolderCreator() {
                    @Override
                    public MZViewHolder createViewHolder() {
                        return new BennerViewHolder();
                    }
                });
            }
        });
    }

    //轮播图适配器
    public static class BennerViewHolder implements MZViewHolder<String> {

        private ImageView imageb1;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.ban_item, null);
            imageb1 = (ImageView) view.findViewById(R.id.imageb1);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            //glide加载图片
            Glide.with(context).load(s).into(imageb1);
        }
    }
}
