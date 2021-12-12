package com.food.myfood.adapter;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.food.myfood.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textview.MaterialTextView;

public class BindingAdapters {
    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelected(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("goneUnless")
    public static void setGoneUnless(View view, boolean visibility) {
        if (visibility) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

    @BindingAdapter("changeImageFavorite")
    public static void chanegeImageFavorite(AppCompatImageView view, boolean isFavorite) {
        if (isFavorite) view.setImageResource(R.drawable.ic_favorite);
        else view.setImageResource(R.drawable.ic_un_favorite);
    }
    @BindingAdapter("changeBackGroundFavorite")
    public static void changeBackGroundFavorite(View view, boolean isFavorite) {
        if (isFavorite) view.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(),R.drawable.bg_favorite));
        else view.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(),R.drawable.bg_un_favorite));
    }

    @BindingAdapter("changeStatusText")
    public static void changeStatusText(MaterialTextView view, boolean isBold) {
        if (isBold) view.setTextColor(ContextCompat.getColor(view.getContext(),R.color.mainText));
        else view.setTextColor(ContextCompat.getColor(view.getContext(),R.color.secondaryText));
    }

    @BindingAdapter("changeStatusImage")
    public static void changeStatusImage(AppCompatImageView view, boolean isBold) {
        if (isBold) view.setImageResource(R.drawable.ic_checked_circle);
        else view.setImageResource(R.drawable.ic_uncheck_circle);
    }

    @BindingAdapter("setImageFromLocalJson")
    public static void setImageFromLocalJson(AppCompatImageView view, String fileName) {
    try {
        int resID = view.getContext().getResources().getIdentifier(fileName, "drawable",  view.getContext().getPackageName());
        view.setImageResource(resID);
    }catch (Exception ex){}
    }
}
