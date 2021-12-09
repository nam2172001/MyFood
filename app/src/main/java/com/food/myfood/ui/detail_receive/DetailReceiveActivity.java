package com.food.myfood.ui.detail_receive;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.food.myfood.R;
import com.food.myfood.databinding.ActivityDetailReceiveBinding;
import com.food.myfood.model.Food;
import com.food.myfood.model.Recipe;
import com.food.myfood.ui.base.BaseMvvmActivity;
import com.food.myfood.ui.main.home.FoodAdapter;
import com.food.myfood.utils.GridSpacingItemDecoration;

import java.util.ArrayList;

public class DetailReceiveActivity extends BaseMvvmActivity<ActivityDetailReceiveBinding, DetailReceiveViewModel> {
    private RecipeAdapter adapterRecipe;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_receive;
    }

    @Override
    protected Class<? extends ViewModel> getViewModelType() {
        return DetailReceiveViewModel.class;
    }

    @Override
    public void onViewReady() {
        setTransParentColor();
        setupRecyclerview();

        getViewDataBinding().viewBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void setupRecyclerview() {
        getViewDataBinding().rvRecipe.setLayoutManager(new GridLayoutManager(this, 1));
        getViewDataBinding().rvRecipe.addItemDecoration(new GridSpacingItemDecoration(1, 1, true));
        getViewDataBinding().rvRecipe.setItemAnimator(new DefaultItemAnimator());
        getViewDataBinding().rvRecipe.setNestedScrollingEnabled(false);
        adapterRecipe = new RecipeAdapter(getRecipes());
        getViewDataBinding().rvRecipe.setAdapter(adapterRecipe);
    }

    private ArrayList<Recipe> getRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Recipe recipe = new Recipe();
            recipe.setName("Phô mai" );
            recipes.add(recipe);
        }

        return recipes;
    }

    private void setTransParentColor(){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}