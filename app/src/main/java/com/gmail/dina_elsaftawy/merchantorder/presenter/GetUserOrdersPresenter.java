package com.gmail.dina_elsaftawy.merchantorder.presenter;

import android.util.Log;

import com.gmail.dina_elsaftawy.merchantorder.model.network.FirebaseCall;

public class GetUserOrdersPresenter implements MainContract.ListOrdersPresenter {

    MainContract.ListOrdersView listOrdersView;

    public GetUserOrdersPresenter(MainContract.ListOrdersView listOrdersView) {
        this.listOrdersView = listOrdersView;
    }

    @Override
    public void getUserOrders() {
        FirebaseCall firebaseCall = new FirebaseCall();
//        if (firebaseCall.getAllOrders().size() != 0)
//            listOrdersView.setListOfOrders(firebaseCall.getAllOrders());
    }
}
