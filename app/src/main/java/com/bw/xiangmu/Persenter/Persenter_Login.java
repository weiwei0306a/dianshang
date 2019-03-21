package com.bw.xiangmu.Persenter;


import com.bw.xiangmu.Contract.Login_Contract;
import com.bw.xiangmu.Model.Model_Login;

/**
 * @Auther: len
 * @Date: 2019/3/1 10:33:12
 * @Description:
 */
public class Persenter_Login implements Login_Contract.Login_Persenter<Login_Contract.Login_View> {

    private Model_Login model_login;
    private Login_Contract.Login_View login_view;

    @Override
    public void attachView(Login_Contract.Login_View login_view) {
        //实例V层
        this.login_view = login_view;
        //创建M层
        model_login = new Model_Login();
    }

    @Override
    public void deatchView(Login_Contract.Login_View login_view) {

    }

    @Override
    public void requestData(String phone, String pwd) {
        model_login.getloginData(phone, pwd, new Login_Contract.Login_Model.Callback_login() {
            @Override
            public void responseData(String message) {
                //把数据返回给V层
                login_view.View_Login(message);
            }
        });
    }
}
