package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import org.is.isadminapp.R;
import org.is.isadminapp.adapter.ChatTeacherSearchAdapter;
import org.is.isadminapp.databinding.ActivitySearchTeacherBinding;
import org.is.isadminapp.model.ChatWithTeacherModel;
import org.is.isadminapp.retrofit.ApiInterface;
import org.is.isadminapp.retrofit.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTeacher extends AppCompatActivity {

    ActivitySearchTeacherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivitySearchTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.notificationBackButton.setOnClickListener(v->onBackPressed());

        binding.btnAdvanceSearch.setOnClickListener(v->{

            String name = binding.emailSearch.getText().toString();

            if (name.equals("")){
                Toast.makeText(this, "Search Field Empty", Toast.LENGTH_SHORT).show();
            }else{
                ChatWithTeacherModel chatWithTeacherModel = new ChatWithTeacherModel(name);
                ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
                Call<ChatWithTeacherModel> call = apiInterface.chatTeacherSearch(chatWithTeacherModel);
                call.enqueue(new Callback<ChatWithTeacherModel>() {
                    @Override
                    public void onResponse(Call<ChatWithTeacherModel> call, Response<ChatWithTeacherModel> response) {
                        if (response.body().getStatus().equals("success")) {
                           List<ChatWithTeacherModel.ApprovedTeacher> list = response.body().getApprovedTeacher();
                            ChatTeacherSearchAdapter chatTeacherSearchAdapter = new ChatTeacherSearchAdapter(list);
                            binding.searchTeacher.setAdapter(chatTeacherSearchAdapter);

                        }else{
                            Toast.makeText(SearchTeacher.this, "No Such Teacher", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ChatWithTeacherModel> call, Throwable t) {

                    }
                });
            }
        });

    }
}