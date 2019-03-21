package com.bw.xiangmu.Contract;

/**
 * @Auther: len
 * @Date: 2019/3/1 10:33:40
 * @Description:
 */
public interface Login_Contract {
    //创建V层
    public interface Login_View {
        //接口方法
        public void View_Login(String message);
    }

    //创建P层
    public interface Login_Persenter<Login_View> {
        //绑定
        public void attachView(Login_View login_view);

        //解绑
        public void deatchView(Login_View login_view);

        //把数据传给M层
        public void requestData(String phone, String pwd);
    }

    //创建M层
    public interface Login_Model {
        //M层的方法
        public void getloginData(String phone, String pwd, Callback_login Callback_login);

        //创建一个回调接口
        public interface Callback_login {
            public void responseData(String message);
        }
    }
}
