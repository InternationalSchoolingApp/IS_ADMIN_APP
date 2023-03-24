package com.example.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.isadminapp.common.ColorOfStatusAndNavBar;
import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.databinding.ActivitySplashPageBinding;
import com.example.isadminapp.preference.PreferenceManager;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;


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
        
        
//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(SplashActivity.this, new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                String token = instanceIdResult.getToken();
//                Log.i("FCM Token", token);
//                saveToken(token);
//            }
//        });



    }

}