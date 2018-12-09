package com.gmail.dina_elsaftawy.merchantorder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.dina_elsaftawy.merchantorder.R;
import com.gmail.dina_elsaftawy.merchantorder.model.entity.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dina_elsaftawy on 12/5/2018.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private List<Order> orders;

    public OrdersAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.order_title);
            desc =  view.findViewById(R.id.order_desc);
        }
    }

    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.MyViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.title.setText(order.getTitle());
        holder.desc.setText(order.getDescription());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void removeOrder(int position) {
        orders.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, orders.size());
    }

    public void modifyItem(final int position, Order order) {
        orders.set(position, order);
        notifyItemChanged(position);
    }

}
