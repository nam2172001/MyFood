package com.food.myfood.ui.main.ads;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.food.myfood.R;
import com.food.myfood.databinding.ActivityMainBinding;
import com.food.myfood.ui.base.BaseMvvmActivity;
import com.food.myfood.ui.main.MainActivity;
import com.food.myfood.ui.main.MainViewModel;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.AudioFocusType;
import com.huawei.hms.ads.splash.SplashView;

public class Splash extends BaseMvvmActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected Class<? extends ViewModel> getViewModelType() {
        return null;
    }

    @Override
    public void onViewReady() {

    }
    private static final String AD_ID = "testq6zq98hecj";
    private static final int AD_TIMEOUT = 10000;
    private static final int MSG_AD_TIMEOUT = 1001;

    /**
     * Pause flag.
     * On the splash ad screen:
     * Set this parameter to true when exiting the app to ensure that the app home screen is not displayed.
     * Set this parameter to false when returning to the splash ad screen from another screen to ensure that the app home screen can be displayed properly.
     */
    private boolean hasPaused = false;

    // Callback processing when an ad display timeout message is received.
    private Handler timeoutHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (Splash.this.hasWindowFocus()) {
                jump();
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashviewads);
        // Load and display a splash ad.
        loadAd();
    }
    /**
     * When the ad display is complete, the app home screen is displayed.
     */
    private void jump() {
        if (!hasPaused) {
            hasPaused = true;
            startActivity(new Intent(Splash.this, MainActivity.class));
            finish();
        }
    }
    /**
     * Set this parameter to true when exiting the app to ensure that the app home screen is not displayed.
     */
    @Override
    protected void onStop() {
        // Remove the timeout message from the message queue.
        timeoutHandler.removeMessages(MSG_AD_TIMEOUT);
        hasPaused = true;
        super.onStop();
    }
    /**
     * Set this parameter to false when returning to the splash ad screen from another screen to ensure that the app home screen can be displayed properly.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        hasPaused = false;
        jump();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private void loadAd() {
        int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        AdParam adParam = new AdParam.Builder().build();
        SplashView.SplashAdLoadListener splashAdLoadListener = new SplashView.SplashAdLoadListener() {
            @Override
            public void onAdLoaded() {
                // Called when an ad is loaded successfully.
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Called when an ad fails to be loaded. The app home screen is then displayed.
                jump();
            }
            @Override
            public void onAdDismissed() {
                // Called when the display of an ad is complete. The app home screen is then displayed.
                jump();
            }
        };
        // Obtain SplashView.
        SplashView splashView = findViewById(R.id.splash_ad_view);
        // Set the default slogan.
        splashView.setSloganResId(R.drawable.image_onboard);
        // Set the audio focus type for a video splash ad.
        splashView.setAudioFocusType(AudioFocusType.NOT_GAIN_AUDIO_FOCUS_WHEN_MUTE);
        // Load the ad. AD_ID indicates the ad unit ID.
        splashView.load(AD_ID, orientation, adParam, splashAdLoadListener);
        // Send a delay message to ensure that the app home screen can be properly displayed after the ad display times out.
        timeoutHandler.removeMessages(MSG_AD_TIMEOUT);
        timeoutHandler.sendEmptyMessageDelayed(MSG_AD_TIMEOUT, AD_TIMEOUT);
    }
}
