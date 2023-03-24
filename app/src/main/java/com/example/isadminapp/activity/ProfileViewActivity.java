package com.example.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.isadminapp.common.ColorOfStatusAndNavBar;
import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.databinding.ActivityProfileViewBinding;
import com.example.isadminapp.model.AdminProfileModel;
import com.example.isadminapp.preference.PreferenceManager;
import com.example.isadminapp.retrofit.ApiInterface;
import com.example.isadminapp.retrofit.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewActivity extends AppCompatActivity {

    private ActivityProfileViewBinding binding;

    ProgressDialog progressDialog;

    PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.colorOfStatusBar(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading");

        binding.profileBackButton.setOnClickListener(v->onBackPressed());

        preferenceManager = new PreferenceManager(this);

        AdminProfileModel adminProfileModel = new AdminProfileModel(preferenceManager.getInt(Constants.USER_ID));

        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<AdminProfileModel> profile = apiInterface.admin(adminProfileModel);
        profile.enqueue(new Callback<AdminProfileModel>() {
            @Override
            public void onResponse(Call<AdminProfileModel> call, Response<AdminProfileModel> response) {
                progressDialog.dismiss();
                if (response.body().getStatus().equals("success")){
                    String url = response.body().getPhoto().replace("sch/", "sch/thumb_");
                    Glide.with(ProfileViewActivity.this).load(url).into(binding.profileImage);
                    binding.nameView.setText(response.body().getName());
                    binding.emailView.setText(response.body().getEmail());
                    binding.roleview.setText(response.body().getRole());
                }
            }

            @Override
            public void onFailure(Call<AdminProfileModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ProfileViewActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });





    }

}