package com.food.myfood.ui.detail_receive;

import androidx.lifecycle.MutableLiveData;

import com.food.myfood.model.Food;
import com.food.myfood.ui.base.BaseViewModel;

public class DetailReceiveViewModel extends BaseViewModel {
    public MutableLiveData<Food> foodResponse = new MutableLiveData<>();
    public void setFood(Food food) {
        foodResponse.setValue(food);
    }
}

