package com.mibarbou.junkfood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.model.Food;
import com.mibarbou.junkfood.model.Order;

import java.util.LinkedList;

/**
 * Created by michel on 08/12/2016.
 */

public class OrdersRecycleViewAdapter extends RecyclerView.Adapter<OrdersRecycleViewAdapter.OrderViewHolder> {
    private LinkedList<Order> mOrders;
    private Context mContext;

    public OrdersRecycleViewAdapter(LinkedList<Order> orders, Context context) {
        super();
        mOrders = orders;
        mContext = context;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_food, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        holder.bindOrder(mOrders.get(position), mContext);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    protected class OrderViewHolder extends  RecyclerView.ViewHolder {
        private TextView mFoodName;
        private TextView mPrice;
        private TextView mAllergens;
        private  TextView mObservations;
        private ImageView mFoodImage;
        private View mView;


        public OrderViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            mFoodName = (TextView) itemView.findViewById(R.id.food_name);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            mAllergens = (TextView) itemView.findViewById(R.id.allergens);
            mObservations = (TextView) itemView.findViewById(R.id.food_observation);
            mFoodImage = (ImageView) itemView.findViewById(R.id.food_image);
        }

        public void  bindOrder(Order order, Context context) {
            Food food = order.getFood();

            mFoodName.setText(food.getName());
            mPrice.setText(String.format("Precio: %.2f€", food.getPrice()));
            mAllergens.setText("Alérgenos: " + food.getAllergens());
            mObservations.setText("");
            mFoodImage.setImageResource(food.getIcon());

        }

        public View getView() {
            return mView;
        }

    }

}

