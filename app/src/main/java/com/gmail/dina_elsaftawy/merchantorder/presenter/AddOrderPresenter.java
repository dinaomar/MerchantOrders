package com.gmail.dina_elsaftawy.merchantorder.presenter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddOrderPresenter implements MainContract.AddOrder {

    MainContract.ListOrdersView listOrdersView;
    private DatabaseReference mDatabase;

    public AddOrderPresenter(MainContract.ListOrdersView listOrdersView) {
        this.listOrdersView = listOrdersView;
    }

    @Override
    public void addNewOrder(String userId, final String orderStringTitle, final String orderStringDescription) {
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }
}
