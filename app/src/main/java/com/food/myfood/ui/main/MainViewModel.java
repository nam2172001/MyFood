package com.food.myfood.ui.main;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.food.myfood.ui.base.BaseViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainViewModel extends BaseViewModel implements BottomNavigationView.OnNavigationItemSelectedListener {
    public MutableLiveData<Integer> selectedTab = new MutableLiveData<>();

    public void setSelectedTab(int tabId) {
        selectedTab.setValue(tabId);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setSelectedTab(item.getItemId());
        return true;
    }
}
