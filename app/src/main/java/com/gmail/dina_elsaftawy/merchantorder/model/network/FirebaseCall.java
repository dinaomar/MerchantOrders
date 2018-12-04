package com.gmail.dina_elsaftawy.merchantorder.model.network;

import com.gmail.dina_elsaftawy.merchantorder.model.data.order;
import com.gmail.dina_elsaftawy.merchantorder.presenter.MainContract;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseCall {

    MainContract.ListOrdersPresenter listOrdersPresenter;
    public DatabaseReference myDatabase;
    private ArrayList<order> orders;

    public ArrayList<order> getAllOrders() {
        myDatabase = FirebaseDatabase.getInstance().getReference();
        orders = new ArrayList<>();
        myDatabase.child("orders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                orders.add((order) dataSnapshot.child("name").getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                orders.remove((String) dataSnapshot.child("name").getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return orders;
    }


}
