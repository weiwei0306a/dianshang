package com.bw.xiangmu.Contract;

/**
 * @Auther: len
 * @Date: 2019/3/19 14:23:58
 * @Description:
 */
public interface DetailsContract {

    public interface DetailsView {

        public void deta_View(String message);
    }

    public interface Details_Persenter<DetailsView> {
        public void attachView(DetailsView detailsView);

        public void deatchView(DetailsView detailsView);

        public void requestdata(int i,int userId, String sessionId);
    }

    public interface Details_Model {
        public void getDeta(int i, int userId, String sessionId, DetaCallback detaCallback);

        public interface DetaCallback {
            public void response(String message);
        }
    }
}
