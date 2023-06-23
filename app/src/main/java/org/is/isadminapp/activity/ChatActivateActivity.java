package org.is.isadminapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.is.isadminapp.retrofit.RetroFitClient;

import org.is.isadminapp.adapter.ChatLogAdapter;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivityChatActivateBinding;
import org.is.isadminapp.model.ActivateChatModel;
import org.is.isadminapp.model.AdminChatActivationLog;
import org.is.isadminapp.model.ChatStatusCheck;
import org.is.isadminapp.preference.PreferenceManager;
import org.is.isadminapp.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatActivateActivity extends AppCompatActivity {

    private ActivityChatActivateBinding binding;

    private List<AdminChatActivationLog> list ;

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
        AdminChatActivationLog adminChatActivationLog = new AdminChatActivationLog(userId);
        Call<AdminChatActivationLog> call1 = apiInterface.getAdminChatLog(adminChatActivationLog);
        call1.enqueue(new Callback<AdminChatActivationLog>() {
            @Override
            public void onResponse(Call<AdminChatActivationLog> call, Response<AdminChatActivationLog> response) {
                if (response.body().getStatus().equals("success")){
                    binding.recyclerViewLelo.setVisibility(View.VISIBLE);
                    List<AdminChatActivationLog.Data> list = response.body().getData();
                    ChatLogAdapter chatLogAdapter = new ChatLogAdapter(list);
                    binding.recyclerViewLelo.setAdapter(chatLogAdapter);
                }
            }

            @Override
            public void onFailure(Call<AdminChatActivationLog> call, Throwable t) {

            }
        });





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
