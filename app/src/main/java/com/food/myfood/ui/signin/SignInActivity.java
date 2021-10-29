package com.food.myfood.ui.signin;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.food.myfood.R;
import com.food.myfood.databinding.ActivitySignInBinding;
import com.food.myfood.ui.base.BaseMvvmActivity;
import com.food.myfood.ui.main.MainActivity;
import com.food.myfood.ui.signup.SignUpActivity;

public class SignInActivity extends BaseMvvmActivity<ActivitySignInBinding, SignInViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected Class<? extends ViewModel> getViewModelType() {
        return SignInViewModel.class;
    }


    @Override
    public void onViewReady() {
        getViewDataBinding().btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        getViewDataBinding().tvSignUp.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }
}