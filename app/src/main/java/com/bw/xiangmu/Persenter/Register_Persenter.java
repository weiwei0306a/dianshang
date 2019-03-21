package com.bw.xiangmu.Persenter;


import com.bw.xiangmu.Contract.Register_Contract;
import com.bw.xiangmu.Model.Register_Moddel;

/**
 * @Auther: len
 * @Date: 2019/3/1 11:20:27
 * @Description:
 */
public class Register_Persenter implements Register_Contract.Register_Persenter<Register_Contract.Register_View> {

    private Register_Moddel register_moddel;
    private Register_Contract.Register_View register_view;

    @Override
    public void attachView(Register_Contract.Register_View register_view) {
        //实例化
        this.register_view = register_view;
        //创建M层
        register_moddel = new Register_Moddel();
    }

    @Override
    public void deatchView(Register_Contract.Register_View register_view) {

    }

    @Override
    public void requestData(String phone, String pwd) {
        register_moddel.getRegisterData(phone, pwd, new Register_Contract.Register_Model.Callback_Register() {
            @Override
            public void responseData(String message) {
                register_view.Model_Register(message);
            }
        });
    }
}
