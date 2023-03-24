package com.example.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.isadminapp.adapter.NotificationAdapter;
import com.example.isadminapp.common.ColorOfStatusAndNavBar;
import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.databinding.ActivityNotificationBinding;
import com.example.isadminapp.model.NotificationForApp;
import com.example.isadminapp.preference.PreferenceManager;
import com.example.isadminapp.retrofit.ApiInterface;
import com.example.isadminapp.retrofit.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;
    ProgressDialog progressDialog;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.loginAndForgetPassword(this);
        binding.notificationList.setHasFixedSize(true);
        progressDialog = new ProgressDialog(this);
        binding.notificationBackButton.setOnClickListener(v-> onBackPressed());
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.notificationList.setLayoutManager(llm);
        preferenceManager = new PreferenceManager(getApplicationContext());
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");


        Integer userId = preferenceManager.getInt(Constants.USER_ID);
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        NotificationForApp notificationForApp = new NotificationForApp(userId);
        Call<NotificationForApp> call = apiInterface.notificationForApp(notificationForApp);
        call.enqueue(new Callback<NotificationForApp>() {
            @Override
            public void onResponse(Call<NotificationForApp> call, Response<NotificationForApp> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    if (response.body().getMessage().equals("Notification got")){
                        List<NotificationForApp.ListForApp> notification = response.body().getListForApp();
                        NotificationAdapter notificationAdapter = new NotificationAdapter(notification);
                        binding.notificationList.setAdapter(notificationAdapter);
                    }else{
                        Toast.makeText(NotificationActivity.this, "No notification", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationForApp> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NotificationActivity.this, "Technical Glitch", Toast.LENGTH_SHORT).show();
            }
        });




    }

}