package com.example.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.databinding.ActivityManageSessionBinding;
import com.example.isadminapp.model.ManageSessionModel;
import com.example.isadminapp.preference.PreferenceManager;
import com.example.isadminapp.retrofit.ApiInterface;
import com.example.isadminapp.retrofit.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageSession extends AppCompatActivity {

    ActivityManageSessionBinding binding;

    PreferenceManager preferenceManager;

    String email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setListeners();

    }

    private void setListeners() {

        binding.btnAdvanceSearch.setOnClickListener(v -> {


            if (binding.emailSearch.getText().toString().trim().equals("")) {
                email = "";
            } else {
                email = binding.emailSearch.getText().toString().trim();
            }

            if (binding.nameSearch.getText().toString().trim().equals("")) {
                name = "";
            } else {
                name = binding.nameSearch.getText().toString().trim();
            }

            search(name, email);

        });


    }

    private void search(String name, String email) {

        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        ManageSessionModel manageSessionModel = new ManageSessionModel(email, name, preferenceManager.getInt(Constants.USER_ID));
        Call<ManageSessionModel> call = apiInterface.searchManageSession(manageSessionModel);
        call.enqueue(new Callback<ManageSessionModel>() {
            @Override
            public void onResponse(Call<ManageSessionModel> call, Response<ManageSessionModel> response) {
                Log.d("TAG", "onResponse: "+ response.body().toString());
            }

            @Override
            public void onFailure(Call<ManageSessionModel> call, Throwable t) {
                Log.d("TAG", "onResponse: "+ t);
            }
        });



    }

    public void init(){
        binding = ActivityManageSessionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(this);
        binding.studentSearchRv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.studentSearchRv.setLayoutManager(llm);
    }

}