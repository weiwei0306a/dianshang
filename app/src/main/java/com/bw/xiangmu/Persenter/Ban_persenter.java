package com.bw.xiangmu.Persenter;

import com.bw.xiangmu.Contract.Ban_Contract;
import com.bw.xiangmu.Model.Ban_Model;

/**
 * @Auther: len
 * @Date: 2019/2/27 20:32:19
 * @Description:
 */
public class Ban_persenter implements Ban_Contract.Contract_Persenter<Ban_Contract.Contract_View> {

    private Ban_Contract.Contract_View contract_view;
    private Ban_Model ban_model;

    @Override
    public void attachView(Ban_Contract.Contract_View contract_view) {
        this.contract_view = contract_view;
        //新建M层
        ban_model = new Ban_Model();
    }

    @Override
    public void deatchView(Ban_Contract.Contract_View contract_view) {

    }

    //轮播
    @Override
    public void requestData() {
        ban_model.getLunData(new Ban_Contract.Contract_Model.Callback_Ban() {
            @Override
            public void responseData(String message) {
                contract_view.Ban_View(message);
            }
        });
    }
    //商品数据
    @Override
    public void ShowData() {
        ban_model.getShowData(new Ban_Contract.Contract_Model.Show_Callback() {
            @Override
            public void Showresponse(String message) {
                contract_view.Show_View(message);
            }
        });
    }

}
