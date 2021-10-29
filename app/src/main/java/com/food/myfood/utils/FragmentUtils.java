package com.food.myfood.utils;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.food.myfood.R;


public class FragmentUtils {

    public static void pushFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data, @Nullable View shareElementView) {
        showFragment(activity, fragment, true, data, null, false, shareElementView);
    }

    public static void replaceFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data, @Nullable View shareElementView) {
        showFragment(activity, fragment, false, data, null, false, shareElementView);
    }

    public static void pushFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragment(activity, fragment, true, data, null, false, null);
    }

    public static void pushFragmentIdContainer(int idContainer, FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragmentIdContainer(idContainer, activity, fragment, true, data, null, false, null);
    }

    public static void replaceFragment(FragmentActivity activity, @NonNull Fragment fragment, @Nullable Bundle data) {
        showFragment(activity, fragment, false, data, null, false, null);
    }

    public static void showFragment(FragmentActivity activity, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, boolean isShowAnimation, @Nullable View shareElementView) {
        if (activity == null) {
            return;
        }

        if (data != null) {
            fragment.setArguments(data);
        }

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        if (isShowAnimation) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && shareElementView != null) {
            fragmentTransaction.addSharedElement(shareElementView, ViewCompat.getTransitionName(shareElementView));
        }

        fragmentTransaction.replace(R.id.container, fragment, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public static void showFragmentIdContainer(int idContainer, FragmentActivity activity, @NonNull Fragment fragment, boolean isPushInsteadOfReplace, @Nullable Bundle data, @Nullable String tag, boolean isShowAnimation, @Nullable View shareElementView) {
        if (activity == null) {
            return;
        }

        if (data != null) {
            fragment.setArguments(data);
        }

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();

        if (isShowAnimation) {
//            fragmentTransaction.setCustomAnimations(R.anim.slide_in_up,
//                    R.anim.slide_out_up);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && shareElementView != null) {
            fragmentTransaction.addSharedElement(shareElementView, ViewCompat.getTransitionName(shareElementView));
        }

        fragmentTransaction.replace(idContainer, fragment, tag);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public static void showFragmentIdContainer(int idContainer, FragmentManager fragmentManager, @NonNull Fragment fragment, boolean isPushInsteadOfReplace) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(idContainer, fragment);
        if (isPushInsteadOfReplace) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}