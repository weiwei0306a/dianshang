package com.bw.xiangmu.Persenter;

import com.bw.xiangmu.Contract.KeyContract;
import com.bw.xiangmu.Model.ModelKey;

/**
 * @Auther: len
 * @Date: 2019/3/20 20:06:00
 * @Description:
 */
public class KeyPersenter implements KeyContract.KeyPersenter<KeyContract.KeyView> {

    private ModelKey modelKey;
    private KeyContract.KeyView keyview;

    @Override
    public void attachView(KeyContract.KeyView keyview) {
        this.keyview=keyview;
        modelKey = new ModelKey();

    }

    @Override
    public void deatchView(KeyContract.KeyView keyview) {

    }

    @Override
    public void KeyData(String key) {
        modelKey.getShowData(key, new KeyContract.KeyView() {
            @Override
            public void Key(String message) {
                keyview.Key(message);
            }
        });
    }

}
