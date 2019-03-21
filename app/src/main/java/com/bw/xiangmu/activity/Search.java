package com.bw.xiangmu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xiangmu.Adapter.Keyadapter;
import com.bw.xiangmu.Bean.KeyBean;
import com.bw.xiangmu.Contract.KeyContract;
import com.bw.xiangmu.Persenter.KeyPersenter;
import com.bw.xiangmu.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Search extends AppCompatActivity implements KeyContract.KeyView {

    @BindView(R.id.pin)
    ImageView pin;
    @BindView(R.id.shu)
    EditText shu;
    @BindView(R.id.sou1)
    TextView sou;
    @BindView(R.id.key_shou)
    RecyclerView keyShou;
    @BindView(R.id.img)
    ImageView img;
    private KeyPersenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        persenter = new KeyPersenter();
        ButterKnife.bind(this);
        persenter.attachView(this);
        sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = shu.getText().toString().trim();
                //判断输入框是否为空
                if (key.equals("")) {
                    img.setVisibility(View.VISIBLE);
                    Toast.makeText(Search.this, "输入框不可以为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                img.setVisibility(View.GONE);
                persenter.KeyData(key);
            }
        });
    }

    //更新UI
    @Override
    public void Key(final String message) {
        runOnUiThread(new Runnable() {

            private List<KeyBean.ResultBean> result;
            private Keyadapter keyadapter;

            @Override
            public void run() {

                Gson gson = new Gson();
                KeyBean keyBean = gson.fromJson(message, KeyBean.class);
                result = keyBean.getResult();
                //判断集合是否为空
                if (result.size() == 0) {
                    img.setVisibility(View.VISIBLE);
                    keyShou.setVisibility(View.GONE);
                } else {
                    img.setVisibility(View.GONE);
                    keyShou.setVisibility(View.VISIBLE);
                    LinearLayoutManager manager = new GridLayoutManager(Search.this, 2);
                    keyShou.setLayoutManager(manager);
                    keyadapter = new Keyadapter(R.layout.key_item, result);
                    keyShou.setAdapter(keyadapter);
                }
                keyShou.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int id = result.get(position).getCommodityId();
                        Intent intent = new Intent(Search.this, details.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        Toast.makeText(Search.this, "" + id, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }

}
