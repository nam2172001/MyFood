package com.food.myfood.ui.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.food.myfood.BR;
import com.food.myfood.R;

import javax.inject.Inject;

public abstract class BaseMvvmActivity<BINDING extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    private BINDING viewDataBinding;
    protected VM viewModel;

    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected BINDING getViewDataBinding() {
        return viewDataBinding;
    }

    protected abstract Class<? extends ViewModel> getViewModelType();

    @SuppressWarnings("unchecked")
    public VM getViewModel() {
        if (this.viewModel == null) {
            this.viewModel = (VM) new ViewModelProvider(this).get(getViewModelType());
        }
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(parseBundle(savedInstanceState));
        handleDataBinding();
        onViewReady();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    protected Bundle parseBundle(Bundle bundle) {
        return bundle;
    }

    private void handleDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        VM viewModel = getViewModel();
        viewDataBinding.setVariable(getBindingVariable(), viewModel);
        viewDataBinding.setLifecycleOwner(this);
        viewDataBinding.executePendingBindings();
    }

    public abstract void onViewReady();

    protected void showToastMessage(int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

}
