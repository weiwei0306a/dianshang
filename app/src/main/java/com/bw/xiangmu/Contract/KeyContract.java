package com.bw.xiangmu.Contract;

/**
 * @Auther: len
 * @Date: 2019/3/20 20:02:21
 * @Description:
 */
public interface KeyContract {
    //创建M层
    public interface KeyView {

        public void Key(String message);
    }
    //P层
    public interface KeyPersenter<KeyView> {
        //绑定
        public void attachView(KeyView keyView);
        //解绑
        public void deatchView(KeyView keyView);
        //交给M层
        public void KeyData(String key);
    }

    //M层
    public interface KeyViewModel {
        public void getShowData(String key,KeyView keyView);

        //商品的回调接口
        public interface KeyView_Callback {
            public void Showresponse(String message);
        }
    }
}
