package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.is.isadminapp.databinding.ActivitySBinding;
import org.is.isadminapp.adapter.ManageUserSearchAdapter;
import org.is.isadminapp.model.ManageUserSearchModel;
import org.is.isadminapp.retrofit.ApiInterface;
import org.is.isadminapp.retrofit.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class S extends AppCompatActivity {

    ActivitySBinding binding;

    String email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.studentSearchRv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.studentSearchRv.setLayoutManager(llm);
        setListeners();

    }

    private void setListeners() {

        Log.d("TAG", "setListeners: worKing ");

        binding.notificationBackButton.setOnClickListener(v->{
            onBackPressed();
        });

        binding.btnAdvanceSearch.setOnClickListener(v -> {
            Log.d("TAG", "btn: worKing ");

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


        ManageUserSearchModel manageUserSearchModel = new ManageUserSearchModel(email, name, 1);
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<ManageUserSearchModel> call = apiInterface.searchManageStudent(manageUserSearchModel);
        call.enqueue(new Callback<ManageUserSearchModel>() {
            @Override
            public void onResponse(Call<ManageUserSearchModel> call, Response<ManageUserSearchModel> response) {
                if (response.body().getStatus().equals("success")) {
                    List<ManageUserSearchModel.List> list = response.body().getList();
                    ManageUserSearchAdapter studentSearchAdapter = new ManageUserSearchAdapter(list);
                    binding.studentSearchRv.setAdapter(studentSearchAdapter);
                }else{
                    Toast.makeText(S.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ManageUserSearchModel> call, Throwable t) {

            }
        });

    }

}