package com.gmail.dina_elsaftawy.merchantorder.presenter;

import com.gmail.dina_elsaftawy.merchantorder.model.network.FirebaseCall;

public class GetUserOrdersPresenter implements MainContract.ListOrdersPresenter {

    MainContract.ListOrdersView listOrdersView;

    @Override
    public void getUserOrders() {
        FirebaseCall firebaseCall = new FirebaseCall();
        if (firebaseCall.getAllOrders().size()!=0)
        listOrdersView.setListOfOrders(firebaseCall.getAllOrders());
    }
}
