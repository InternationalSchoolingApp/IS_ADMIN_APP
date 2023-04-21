package com.example.isadminapp.activity;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Toast;

import com.example.isadminapp.adapter.StudentSearchChatAdapter;
import com.example.isadminapp.databinding.ActivitySearchStudentForChatBinding;
import com.example.isadminapp.model.StudentAvailablityForChatModel;
import com.example.isadminapp.retrofit.ApiInterface;
import com.example.isadminapp.retrofit.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchStudentForChatActivity extends AppCompatActivity {

    private ActivitySearchStudentForChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchStudentForChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdvanceSearch.setOnClickListener(v->{

            String name = binding.emailSearch.getText().toString();

            if (name.equals("")){
                Toast.makeText(this, "Search Field Empty", Toast.LENGTH_SHORT).show();
            }else{
                StudentAvailablityForChatModel studentAvailablityForChatModel = new StudentAvailablityForChatModel(name);
                ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
                Call<StudentAvailablityForChatModel> call = apiInterface.getStudentsForChat(studentAvailablityForChatModel);
                call.enqueue(new Callback<StudentAvailablityForChatModel>() {
                    @Override
                    public void onResponse(Call<StudentAvailablityForChatModel> call, Response<StudentAvailablityForChatModel> response) {
                        if(response.body().getStatus().equals("success")){
                            StudentSearchChatAdapter studentSearchChatAdapter = new StudentSearchChatAdapter(response.body().getStudentList());
                            binding.searchStudent.setAdapter(studentSearchChatAdapter);
                        }else{
                            Toast.makeText(SearchStudentForChatActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StudentAvailablityForChatModel> call, Throwable t) {

                    }
                });
            }



    });
}

}