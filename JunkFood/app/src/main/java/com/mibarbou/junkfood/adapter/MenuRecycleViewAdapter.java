package com.mibarbou.junkfood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mibarbou.junkfood.R;
import com.mibarbou.junkfood.activity.MenuActivity;
import com.mibarbou.junkfood.model.Food;

import java.util.LinkedList;

/**
 * Created by michel on 10/12/2016.
 */

public class MenuRecycleViewAdapter extends RecyclerView.Adapter<MenuRecycleViewAdapter.MenuViewHolder> {

    private LinkedList<Food> mFoods;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public MenuRecycleViewAdapter(LinkedList<Food> foods, OnItemClickListener onItemClickListener) {
        super();
        mFoods = foods;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public MenuRecycleViewAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_food, parent, false);
        return new MenuRecycleViewAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuRecycleViewAdapter.MenuViewHolder holder, final int position) {
        holder.bindFood(mFoods.get(position), mContext);
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food selectedFood = mFoods.get(position);
                mOnItemClickListener.onFoodSelected(selectedFood);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    protected class MenuViewHolder extends  RecyclerView.ViewHolder {
        private TextView mFoodName;
        private TextView mPrice;
        private TextView mAllergens;
        private  TextView mObservations;
        private ImageView mFoodImage;
        private View mView;


        public MenuViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            mFoodName = (TextView) itemView.findViewById(R.id.food_name);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            mAllergens = (TextView) itemView.findViewById(R.id.allergens);
            mObservations = (TextView) itemView.findViewById(R.id.food_observation);
            mFoodImage = (ImageView) itemView.findViewById(R.id.food_image);
        }

        public void  bindFood(Food food, Context context) {

            mFoodName.setText(food.getName());
            mPrice.setText(String.format("Precio: %.2f€", food.getPrice()));
            mAllergens.setText(food.getAllergens());
            mObservations.setText("");
            mFoodImage.setImageResource(food.getIcon());

        }

        public View getView() {
            return mView;
        }

    }

    public interface OnItemClickListener {
        public void onFoodSelected(Food food);
    }

}


