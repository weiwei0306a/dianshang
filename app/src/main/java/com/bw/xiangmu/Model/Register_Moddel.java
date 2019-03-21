package com.bw.xiangmu.Model;


import com.bw.xiangmu.Contract.Register_Contract;
import com.bw.xiangmu.Utils.OkhttpUtils;
import com.bw.xiangmu.api.api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: len
 * @Date: 2019/3/1 11:20:03
 * @Description:
 */
public class Register_Moddel implements Register_Contract.Register_Model {
    @Override
    public void getRegisterData(String phone, String pwd, final Callback_Register callback_register) {
        //请求数据
        OkhttpUtils.HttpPost(api.url_register + "?phone=" + phone + "&pwd=" + pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.body().string();
                callback_register.responseData(message);
            }
        });
    }
}
