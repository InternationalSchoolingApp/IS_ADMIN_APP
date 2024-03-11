package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivitySplashPageBinding;
import org.is.isadminapp.preference.PreferenceManager;


public class SplashPage extends AppCompatActivity {

    PreferenceManager preferenceManager ;

    private ActivitySplashPageBinding binding;


    private int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        boolean areNotificationsEnabled = notificationManager.areNotificationsEnabled();
        if (!areNotificationsEnabled) {
            SPLASH_TIME = 15000;
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
            startActivity(intent);
            finish();
        }

        binding = ActivitySplashPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(this);



        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.colorOfStatusBar(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean check = preferenceManager.getBoolean(Constants.USER_LOGGED);

                Intent intent;
                if (check) {
                    intent = new Intent(SplashPage.this, DashboardActivity.class);
                } else {
                    intent = new Intent(SplashPage.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);



    }

}