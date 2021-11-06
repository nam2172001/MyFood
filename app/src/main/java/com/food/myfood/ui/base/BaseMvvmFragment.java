package com.food.myfood.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

//import javax.inject.Inject;


public abstract class BaseMvvmFragment<BINDING extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    protected BINDING viewDataBinding;
    protected VM viewModel;
    private boolean alreadyLoaded = false;

//    protected int getBindingVariable() {
//        return BR.viewModel;
//    }

    @LayoutRes
    public abstract int getLayoutId();

    protected abstract Class<? extends androidx.lifecycle.ViewModel> getViewModelType();

    public BINDING getViewDataBinding() {
        return viewDataBinding;
    }

    @SuppressWarnings("unchecked")
    public VM getViewModel() {
        if (this.viewModel == null) {
            this.viewModel = (VM) new ViewModelProvider(this).get(getViewModelType());
        }
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = getViewModel();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false);
        handleDataBinding();
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewReady();
    }



    protected void handleDataBinding() {
        viewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
        // viewDataBinding.setVariable(getBindingVariable(), viewModel);
        viewDataBinding.executePendingBindings();
    }


    public abstract void onViewReady();


    public <ViewModel extends androidx.lifecycle.ViewModel> ViewModel getCommonViewModel(Class<ViewModel> vmClass) {
        return new ViewModelProvider(requireActivity()).get(vmClass);
    }

}