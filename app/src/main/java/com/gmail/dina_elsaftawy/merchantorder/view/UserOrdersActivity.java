package com.gmail.dina_elsaftawy.merchantorder.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.dina_elsaftawy.merchantorder.R;
import com.gmail.dina_elsaftawy.merchantorder.model.data.Order;
import com.gmail.dina_elsaftawy.merchantorder.presenter.AddOrderPresenter;
import com.gmail.dina_elsaftawy.merchantorder.presenter.MainContract;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserOrdersActivity extends AppCompatActivity implements MainContract.ListOrdersView {

    @BindView(R.id.userNameDisplay)
    TextView userNameDisplay;
    private String userID;
    private MainContract.AddOrder addOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        addOrderPresenter = new AddOrderPresenter(this);
        Intent intent = getIntent();
        userNameDisplay.setText(intent.getStringExtra("userName"));
        userID = intent.getStringExtra("userId");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAndShowAlertDialog();
            }
        });
    }

    private void createAndShowAlertDialog() {
        LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.order_dialog, null);

        final EditText input1 = (EditText) textEntryView.findViewById(R.id.orderTitle);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.orderDesc);


        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.mipmap.ic_launcher_round).setTitle("Add Your Order").setView(textEntryView).setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        if (TextUtils.isEmpty(input1.getText()) || TextUtils.isEmpty(input1.getText())) {
                            Toast.makeText(UserOrdersActivity.this, "please enter valid order data",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            addOrderDataToFirebase(input1.getText().toString(), input2.getText().toString());
                            // update adapter

                            dialog.dismiss();
                        }

                    }
                }).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        dialog.dismiss();
                    }
                });
        alert.show();
    }

    private void addOrderDataToFirebase(String s, String s1) {
        Order order = new Order(s, s1, userNameDisplay.getText().toString());
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("orders").push().getKey();
        mDatabase.child("orders").child(key).setValue(order);
    }


    @Override
    public void setOrder(Order order) {
        Log.w("order", order + "");
    }
}
