package com.food.myfood.ui.detail_receive;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.food.myfood.R;
import com.food.myfood.databinding.ItemRecipeBinding;
import com.food.myfood.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private List<Recipe> postList;
    private LayoutInflater layoutInflater;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecipeBinding binding;

        public MyViewHolder(final ItemRecipeBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public RecipeAdapter(List<Recipe> postList) {
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemRecipeBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_recipe, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.binding.setItem(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

}