package com.bw.xiangmu.Model;

import com.bw.xiangmu.Contract.KeyContract;
import com.bw.xiangmu.Utils.OkhttpUtils;
import com.bw.xiangmu.api.api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: len
 * @Date: 2019/3/20 20:08:36
 * @Description:
 */
public class ModelKey implements KeyContract.KeyViewModel {
    int page=1;
    int count=5;
    @Override
    public void getShowData(String key, final KeyContract.KeyView keyView) {
        OkhttpUtils.HttpGet(api.url_key+"?page="+page+"&count="+count+"&keyword="+key, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                keyView.Key(message);
            }
        });
    }
}
