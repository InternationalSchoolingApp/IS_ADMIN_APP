package com.example.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.isadminapp.adapter.WatchTeacherSearchAdapter;
import com.example.isadminapp.databinding.ActivityChatSearchBinding;
import com.example.isadminapp.model.ChatWithTeacherModel;
import com.example.isadminapp.retrofit.ApiInterface;
import com.example.isadminapp.retrofit.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatSearchActivity extends AppCompatActivity {

    ActivityChatSearchBinding binding;
    String student, teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();

    }

    private void setListener() {

        binding.notificationBackButton.setOnClickListener(v->onBackPressed());

        binding.btnAdvanceSearch.setOnClickListener(v->{
            if (!binding.nameSearch.getText().toString().toLowerCase().trim().equals("") ) {
                teacher = binding.nameSearch.getText().toString().toLowerCase().trim();
                search(teacher);
            }else{
                Toast.makeText(this, "Field Empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void search(String teacher) {

        ChatWithTeacherModel chatWithTeacherModel = new ChatWithTeacherModel(teacher);
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<ChatWithTeacherModel> call = apiInterface.chatTeacherSearch(chatWithTeacherModel);
        call.enqueue(new Callback<ChatWithTeacherModel>() {
            @Override
            public void onResponse(Call<ChatWithTeacherModel> call, Response<ChatWithTeacherModel> response) {
                if (response.body().getStatus().equals("success")) {
                    List<ChatWithTeacherModel.ApprovedTeacher> list = response.body().getApprovedTeacher();
                    WatchTeacherSearchAdapter chatTeacherSearchAdapter = new WatchTeacherSearchAdapter(list);
                    binding.chatRecyclerView.setAdapter(chatTeacherSearchAdapter);

                }else{
                    Toast.makeText(ChatSearchActivity.this, "No Such Teacher", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatWithTeacherModel> call, Throwable t) {

            }
        });
    }

        }

