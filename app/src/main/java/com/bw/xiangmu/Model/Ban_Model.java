package com.bw.xiangmu.Model;


import com.bw.xiangmu.Contract.Ban_Contract;
import com.bw.xiangmu.Utils.OkhttpUtils;
import com.bw.xiangmu.api.api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: len
 * @Date: 2019/2/27 20:32:32
 * @Description:
 */
public class Ban_Model implements Ban_Contract.Contract_Model {

    @Override
    public void getLunData(final Callback_Ban callback_ban) {
        OkhttpUtils.HttpGet(api.url_ban, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callback_ban.responseData(message);
            }
        });
    }
    //商品请请求数据
    @Override
    public void getShowData(final Show_Callback show_callback) {
        OkhttpUtils.HttpGet(api.url_home, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                show_callback.Showresponse(message);
            }
        });
    }
}
