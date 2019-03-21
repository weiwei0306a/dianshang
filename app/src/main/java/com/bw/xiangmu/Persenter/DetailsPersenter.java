package com.bw.xiangmu.Persenter;

import com.bw.xiangmu.Contract.DetailsContract;
import com.bw.xiangmu.Model.DetailsModel;

/**
 * @Auther: len
 * @Date: 2019/3/19 14:25:47
 * @Description:
 */
public class DetailsPersenter implements DetailsContract.Details_Persenter<DetailsContract.DetailsView> {

    private DetailsModel detailsModel;
    private DetailsContract.DetailsView detailsView;

    @Override
    public void attachView(DetailsContract.DetailsView detailsView) {
        detailsModel = new DetailsModel();
        this.detailsView = detailsView;
    }

    @Override
    public void deatchView(DetailsContract.DetailsView detailsView) {

    }

    @Override
    public void requestdata(int id,int userId, String sessionId) {
        detailsModel.getDeta(id,userId ,sessionId,new DetailsContract.Details_Model.DetaCallback() {
            @Override
            public void response(String message) {
                detailsView.deta_View(message);
            }
        });
    }
}
