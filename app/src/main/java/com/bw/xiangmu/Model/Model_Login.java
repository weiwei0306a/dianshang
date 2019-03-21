package com.bw.xiangmu.Model;


import com.bw.xiangmu.Contract.Login_Contract;
import com.bw.xiangmu.Utils.OkhttpUtils;
import com.bw.xiangmu.api.api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: len
 * @Date: 2019/3/1 10:41:20
 * @Description:
 */
public class Model_Login implements Login_Contract.Login_Model {


    @Override
    public void getloginData(String phone, String pwd, final Callback_login Callback_login) {
        OkhttpUtils.HttpPost(api.url_login + "?phone=" + phone + "&pwd=" + pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                Callback_login.responseData(message);
            }
        });
    }
}
