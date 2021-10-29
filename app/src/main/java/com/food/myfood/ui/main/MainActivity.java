package com.food.myfood.ui.main;

import androidx.lifecycle.ViewModel;

import com.food.myfood.R;
import com.food.myfood.databinding.ActivityMainBinding;
import com.food.myfood.ui.base.BaseMvvmActivity;
import com.food.myfood.ui.main.home.HomeFragment;
import com.food.myfood.ui.main.profile.ProfileFragment;
import com.food.myfood.utils.FragmentUtils;

public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<? extends ViewModel> getViewModelType() {
        return MainViewModel.class;
    }


    @Override
    public void onViewReady() {
        getViewModel().selectedTab.observe(this, selectedTab -> {
            switch (selectedTab) {
                case R.id.navigation_profile:
                    showProfile();
                    break;
                case R.id.navigation_home:
                    showHome();
                    break;
            }
        });

        getViewModel().setSelectedTab(R.id.navigation_home);
        getViewDataBinding().navigationMain.setSelectedItemId(R.id.navigation_home);
    }

    private void showProfile(){
        ProfileFragment profileFragment = (ProfileFragment) getSupportFragmentManager()
                .findFragmentByTag(ProfileFragment.TAG);
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
        }
        if (!profileFragment.isAdded()) {
            FragmentUtils.showFragment(this, profileFragment, false, null, ProfileFragment.TAG, true, null);
        }
    }

    private void showHome(){
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager()
                .findFragmentByTag(HomeFragment.TAG);
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
        }
        if (!homeFragment.isAdded()) {
            FragmentUtils.showFragment(this, homeFragment, false, null, HomeFragment.TAG, true, null);
        }
    }
}