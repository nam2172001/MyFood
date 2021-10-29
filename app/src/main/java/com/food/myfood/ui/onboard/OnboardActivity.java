package com.food.myfood.ui.onboard;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;


import com.food.myfood.R;
import com.food.myfood.databinding.ActivityOnboardBinding;
import com.food.myfood.ui.base.BaseMvvmActivity;
import com.food.myfood.ui.signin.SignInActivity;
import com.food.myfood.ui.signup.SignUpActivity;

public class OnboardActivity extends BaseMvvmActivity<ActivityOnboardBinding, OnboardViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_onboard;
    }

    @Override
    protected Class<? extends ViewModel> getViewModelType() {
        return OnboardViewModel.class;
    }


    @Override
    public void onViewReady() {
        getViewDataBinding().btnStart.setOnClickListener(view -> {
            startActivity(new Intent(this, SignInActivity.class));
        });
    }
}