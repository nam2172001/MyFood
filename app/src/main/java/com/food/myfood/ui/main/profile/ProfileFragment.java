package com.food.myfood.ui.main.profile;


import android.content.Intent;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.food.myfood.R;
import com.food.myfood.databinding.FragmentProfileBinding;
import com.food.myfood.model.Food;
import com.food.myfood.ui.base.BaseMvvmFragment;
import com.food.myfood.ui.detail_receive.DetailReceiveActivity;
import com.food.myfood.ui.main.home.FoodAdapter;
import com.food.myfood.ui.signin.SignInActivity;
import com.food.myfood.utils.GridSpacingItemDecoration;

import java.util.ArrayList;

public class ProfileFragment extends BaseMvvmFragment<FragmentProfileBinding, ProfileViewModel> implements FoodAdapter.FoodAdapterListener {

    public static final String TAG = "ProfileFragment";
    private FoodAdapter adapterFood;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected Class<? extends ViewModel> getViewModelType() {
        return ProfileViewModel.class;
    }

    @Override
    public void onViewReady() {
        setupRecyclerview();
    }

    private void setupRecyclerview() {
        getViewDataBinding().rvFood.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        getViewDataBinding().rvFood.addItemDecoration(new GridSpacingItemDecoration(2, 1, true));
        getViewDataBinding().rvFood.setItemAnimator(new DefaultItemAnimator());
        getViewDataBinding().rvFood.setNestedScrollingEnabled(false);
        adapterFood = new FoodAdapter(getFoods(), this);
        getViewDataBinding().rvFood.setAdapter(adapterFood);
    }

    @Override
    public void onFoodClicked(Food post) {
        startActivity(new Intent(requireActivity(), DetailReceiveActivity.class));
    }

}