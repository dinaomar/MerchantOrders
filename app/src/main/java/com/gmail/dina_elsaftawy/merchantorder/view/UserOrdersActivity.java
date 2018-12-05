package com.gmail.dina_elsaftawy.merchantorder.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.dina_elsaftawy.merchantorder.R;
import com.gmail.dina_elsaftawy.merchantorder.adapter.OrdersAdapter;
import com.gmail.dina_elsaftawy.merchantorder.adapter.RecyclerTouchListener;
import com.gmail.dina_elsaftawy.merchantorder.model.data.Order;
import com.gmail.dina_elsaftawy.merchantorder.presenter.AddOrderPresenter;
import com.gmail.dina_elsaftawy.merchantorder.presenter.MainContract;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserOrdersActivity extends AppCompatActivity implements MainContract.ListOrdersView {

    @BindView(R.id.userNameDisplay)
    TextView userNameDisplay;
    private String userID;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    OrdersAdapter ordersAdapter;
    List<Order> orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        userNameDisplay.setText(intent.getStringExtra("userName"));
        userID = intent.getStringExtra("userId");
        orders = new ArrayList<>();
        ordersAdapter = new OrdersAdapter(orders);

        getOrdersListFromFireBase();
        recyclerView.setAdapter(ordersAdapter);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                // on click order will be deleted
                new AlertDialog.Builder(UserOrdersActivity.this)
                        .setTitle("Remove Order")
                        .setMessage("Are you sure you want to delete this order?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                String id = orders.get(position).getOrderId();
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("orders").child(id).removeValue();
                                ordersAdapter.removeOrder(position);

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }

            @Override
            public void onLongClick(View view, int position) {
                // on long click order will be edited
                String orderId = orders.get(position).getOrderId();
                String oldTitle = orders.get(position).getTitle();
                String oldDesc = orders.get(position).getDescription();
                showEditAlertDialog(position, orderId, oldTitle, oldDesc);
            }
        }));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAndShowAlertDialog();
            }
        });
    }

    private void showEditAlertDialog(final int position, final String orderId, String oldTitle, String oldDesc) {
        LayoutInflater factory = LayoutInflater.from(this);

        final View textEntryView = factory.inflate(R.layout.order_dialog, null);

        final EditText input1 = (EditText) textEntryView.findViewById(R.id.orderTitle);
        final EditText input2 = (EditText) textEntryView.findViewById(R.id.orderDesc);

        input1.setText(oldTitle);
        input2.setText(oldDesc);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(R.mipmap.ic_launcher_round).setTitle("Update Your Order").setView(textEntryView).setPositiveButton("Change",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        if (TextUtils.isEmpty(input1.getText()) || TextUtils.isEmpty(input1.getText())) {
                            Toast.makeText(UserOrdersActivity.this, "please enter valid order data",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            DatabaseReference mDatabase;
                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            Order order = new Order(input1.getText().toString(), input2.getText().toString(),
                                    userNameDisplay.getText().toString(), orderId);
                            mDatabase.child("orders").child(orderId).setValue(order);
                            ordersAdapter.modifyItem(position, order);
                            // update adapter
                            ordersAdapter.notifyDataSetChanged();
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

    private List<Order> getOrdersListFromFireBase() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersdRef = rootRef.child("orders");
        Query applesQuery = rootRef.orderByChild("orders");
        applesQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Order order = new Order();
                    String user = ds.child("userName").getValue(String.class);
                    if (userNameDisplay.getText().toString().equals(user)) {
                        order.title = ds.child("title").getValue(String.class);
                        order.description = ds.child("description").getValue(String.class);
                        order.orderId = ds.child("orderId").getValue(String.class);
                        Log.w("title", order.getTitle());
                        orders.add(order);
                    }
                }
                recyclerView.setAdapter(ordersAdapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        return orders;
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
                            ordersAdapter.notifyItemInserted(orders.size() - 1);
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
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("orders").push().getKey();
        Order order = new Order(s, s1, userNameDisplay.getText().toString(), key);
        mDatabase.child("orders").child(key).setValue(order);
        orders.add(order);
    }

    @Override
    public void setOrder(Order order) {
        Log.w("order", order + "");
    }
}
