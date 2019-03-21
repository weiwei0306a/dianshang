package com.bw.xiangmu.Contract;

/**
 * @Auther: len
 * @Date: 2019/2/27 20:23:46
 * @Description:
 */
public interface Ban_Contract {
    //V层
    public interface Contract_View {
        //首页商品
        public void Show_View(String message);

        //轮播
        public void Ban_View(String message);
    }
    //P层
    public interface Contract_Persenter<Contract_View> {
        //绑定
        public void attachView(Contract_View contract_view);
        //解绑
        public void deatchView(Contract_View contract_view);
        //交给M层
        public void requestData();
        //首页商品
        public void ShowData();
    }

    //M层
    public interface Contract_Model {
        public void getLunData(Callback_Ban callback_ban);

        public void getShowData(Show_Callback show_callback);

        //商品的回调接口
        public interface Show_Callback {
            public void Showresponse(String message);
        }
        //接口回调
        public interface Callback_Ban {
            //返回数据
            public void responseData(String message);
        }
    }


}
