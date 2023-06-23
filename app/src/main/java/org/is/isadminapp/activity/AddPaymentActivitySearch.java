package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.is.isadminapp.adapter.StudentSearchAdapter;
import org.is.isadminapp.databinding.ActivityAddPaymentSearchBinding;
import org.is.isadminapp.model.AddPaymentStudentSearchModel;
import org.is.isadminapp.retrofit.ApiInterface;
import org.is.isadminapp.retrofit.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPaymentActivitySearch extends AppCompatActivity {


    ActivityAddPaymentSearchBinding binding;

    String email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPaymentSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.studentSearchRv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.studentSearchRv.setLayoutManager(llm);
        setListeners();



    }

    private void setListeners() {

        Log.d("TAG", "setListeners: worKing ");



        binding.btnAdvanceSearch.setOnClickListener(v -> {
            Log.d("TAG", "btn: worKing ");

            if (binding.emailSearch.getText().toString().trim().equals("")){
                email = "";
            } else {
                email = binding.emailSearch.getText().toString().trim();
            }

            if (binding.nameSearch.getText().toString().trim().equals("")){
                name = "";
            } else {
                name = binding.nameSearch.getText().toString().trim();
            }

            search(name , email);

        });

    }

    private void search(String name, String email) {

        Toast.makeText(this, "in Method", Toast.LENGTH_SHORT).show();

        AddPaymentStudentSearchModel addPaymentStudentSearchModel = new AddPaymentStudentSearchModel(email, name);
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<AddPaymentStudentSearchModel> call = apiInterface.searchStudent(addPaymentStudentSearchModel);
        call.enqueue(new Callback<AddPaymentStudentSearchModel>() {
            @Override
            public void onResponse(Call<AddPaymentStudentSearchModel> call, Response<AddPaymentStudentSearchModel> response) {

                if (response.body().getStatus().equals("success")){
                    List<AddPaymentStudentSearchModel.List> list = response.body().getList();
                    StudentSearchAdapter studentSearchAdapter = new StudentSearchAdapter(list);
                    binding.studentSearchRv.setAdapter(studentSearchAdapter);
                }

            }

            @Override
            public void onFailure(Call<AddPaymentStudentSearchModel> call, Throwable t) {

            }
        });

    }


}