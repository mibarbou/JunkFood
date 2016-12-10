package com.mibarbou.junkfood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mibarbou.junkfood.model.Foods;
import com.mibarbou.junkfood.model.Order;

import java.util.LinkedList;

/**
 * Created by michel on 08/12/2016.
 */

public class OrdersRecycleViewAdapter extends RecyclerView.Adapter<OrdersRecycleViewAdapter.OrderViewHolder> {
    private LinkedList<Order> mOrders;

    public OrdersRecycleViewAdapter(LinkedList<Order> orders, Context context) {
        super();
        mOrders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    protected class OrderViewHolder extends  RecyclerView.ViewHolder {

        public OrderViewHolder(View itemView) {
            super(itemView);
        }
    }

}

