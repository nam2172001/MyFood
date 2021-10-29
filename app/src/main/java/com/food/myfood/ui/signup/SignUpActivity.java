package com.food.myfood.ui.signup;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.food.myfood.R;
import com.food.myfood.databinding.ActivitySignUpBinding;
import com.food.myfood.ui.base.BaseMvvmActivity;
import com.food.myfood.ui.signin.SignInActivity;

public class SignUpActivity extends BaseMvvmActivity<ActivitySignUpBinding, SignUpViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected Class<? extends ViewModel> getViewModelType() {
        return SignUpViewModel.class;
    }

    @Override
    public void onViewReady() {
        getViewDataBinding().edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    if (s.toString().length() > 6){
                        getViewModel().setValidText(true);
                    }else {
                        getViewModel().setValidText(false);
                    }
                    getViewModel().setShowRequire(true);
                    getViewModel().setIsNumber(hasNumber(s.toString()));
                } else {
                    getViewModel().setShowRequire(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getViewDataBinding().btnSignUp.setOnClickListener(view -> {
            startActivity(new Intent(this, SignInActivity.class));
        });
    }

    private boolean hasNumber(String text) {
        if (text.matches(".*\\d.*")) {
            return true;
        } else {
            return false;
        }
    }
}