package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        }, 3000);



    }

}