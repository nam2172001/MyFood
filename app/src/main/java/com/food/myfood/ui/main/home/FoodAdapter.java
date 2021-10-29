package com.food.myfood.ui.main.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.food.myfood.R;
import com.food.myfood.databinding.ItemFoodBinding;
import com.food.myfood.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private List<Food> postList;
    private LayoutInflater layoutInflater;
    private FoodAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemFoodBinding binding;

        public MyViewHolder(final ItemFoodBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }


    public FoodAdapter(List<Food> postList, FoodAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemFoodBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_food, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.binding.setItem(postList.get(position));
        holder.binding.getRoot().setOnClickListener(v -> {
            if (listener != null) {
                listener.onFoodClicked(postList.get(position));
            }
        });
        holder.binding.backgroundImage.setOnClickListener(view -> {
            postList.get(position).setFavorite(!postList.get(position).getFavorite());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface FoodAdapterListener {
        void onFoodClicked(Food post);
    }
}