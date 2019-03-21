package com.bw.xiangmu.Model;

import android.util.Log;

import com.bw.xiangmu.Contract.DetailsContract;
import com.bw.xiangmu.Utils.OkhttpUtils;
import com.bw.xiangmu.api.api;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: len
 * @Date: 2019/3/19 14:31:26
 * @Description:
 */
public class DetailsModel implements DetailsContract.Details_Model {


    @Override
    public void getDeta(int id, int userId, String sessionId, final DetaCallback detaCallback) {
        OkGo.<String>get(api.url_details + "?commodityId=" + id)
                .headers("userId", userId + "")
                .headers("sessionId", sessionId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String string = response.body().toString();
                        detaCallback.response(string);
                    }
                });
    }
}
