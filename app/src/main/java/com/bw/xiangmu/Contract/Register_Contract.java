package com.bw.xiangmu.Contract;

/**
 * @Auther: len
 * @Date: 2019/3/1 11:11:54
 * @Description:
 */
public interface Register_Contract {
    //创建V层接口
    public interface Register_View {
        public void Model_Register(String message);
    }

    //创建P层接口
    public interface Register_Persenter<Register_Moddel> {
        //绑定
        public void attachView(Register_Moddel register_moddel);

        //解绑
        public void deatchView(Register_Moddel register_moddel);

        //把数据传给V层
        public void requestData(String phone, String pwd);
    }

    //V层
    public interface Register_Model {
        //接收M层的数据去请求
        public void getRegisterData(String phone, String pwd, Callback_Register callback_register);

        //数据回调接口
        public interface Callback_Register {
            public void responseData(String message);
        }
    }
}
