package com.example.isadminapp.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.databinding.ActivityChatActivateBinding;
import com.example.isadminapp.model.ActivateChatModel;
import com.example.isadminapp.model.ChatStatusCheck;
import com.example.isadminapp.preference.PreferenceManager;
import com.example.isadminapp.retrofit.ApiInterface;
import com.example.isadminapp.retrofit.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivateActivity extends AppCompatActivity {

    private ActivityChatActivateBinding binding;

    Integer status ;

    PreferenceManager preferenceManager;

    ProgressDialog progressDialog;




    Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatActivateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        binding.recentTeacherBackButton.setOnClickListener(v->onBackPressed());

        userId = preferenceManager.getInt(Constants.USER_ID);

        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        ChatStatusCheck chatStatusCheck = new ChatStatusCheck(userId);
        Call<ChatStatusCheck> call = apiInterface.chatStatusCheck(chatStatusCheck);
        call.enqueue(new Callback<ChatStatusCheck>() {
            @Override
            public void onResponse(Call<ChatStatusCheck> call, Response<ChatStatusCheck> response) {
                if (response.body().getStatus().equals("not Active")){
                    binding.currentState.setText("Not Active Click Button Below to Active Chat");
                    binding.activityButton.setText("Activate");
                    progressDialog.dismiss();
                    status = 1;
                    binding.activityButton.setOnClickListener(v->changeStatus(v,status));
                }else if (response.body().getStatus().equals("failed")){
                    binding.currentState.setText("Already Active Click Button Below to De-Activate");
                    binding.activityButton.setText("De Activate");
                    progressDialog.dismiss();
                    status = 0;
                    binding.activityButton.setOnClickListener(v->changeStatus(v, status));
                } else {
                    Log.d("TAG", "onResponse: " + response.body().getStatus());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ChatStatusCheck> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t);
            }
        });






    }

    private void changeStatus(View v, Integer status) {

        ActivateChatModel activateChatModel = new ActivateChatModel(userId);
        if (status == 0){
            ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
            Call<ActivateChatModel> call = apiInterface.deactivate(activateChatModel);
            call.enqueue(new Callback<ActivateChatModel>() {
                @Override
                public void onResponse(Call<ActivateChatModel> call, Response<ActivateChatModel> response) {
                    Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ChatActivateActivity.this, "Deactivated Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ActivateChatModel> call, Throwable t) {

                }
            });
        }else{
            ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
            Call<ActivateChatModel> call = apiInterface.activate(activateChatModel);
            call.enqueue(new Callback<ActivateChatModel>() {
                @Override
                public void onResponse(Call<ActivateChatModel> call, Response<ActivateChatModel> response) {
                    Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ChatActivateActivity.this, "Activated Successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ActivateChatModel> call, Throwable t) {

                }
            });
        }
    }

}
