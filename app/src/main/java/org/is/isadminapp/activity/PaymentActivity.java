package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.is.isadminapp.adapter.PaymentAdapter;
import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivityPaymentBinding;
import org.is.isadminapp.model.PaymentSearchModel;
import org.is.isadminapp.preference.PreferenceManager;
import org.is.isadminapp.retrofit.ApiInterface;
import org.is.isadminapp.retrofit.RetroFitClient;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    private int startDate = 0;

    private PreferenceManager preferenceManager;
    private int endDate = 0;

    private int startDay = 0;
    private int startMonth = 0;
    private int startYear = 0;

    private int endDay = 0;
    private int endMonth = 0;
    private int endYear = 0;
    private String name;
    private String sDate;
    private String eDate;
    private ActivityPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ColorOfStatusAndNavBar color = new ColorOfStatusAndNavBar();
        color.loginAndForgetPassword(this);

        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerViewPayment.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewPayment.setLayoutManager(llm);
        preferenceManager = new PreferenceManager(this);


        setListeners();


    }

    private void setListeners() {

        binding.backButtonNotes.setOnClickListener(v->{
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });

        binding.enterStartDate.setOnClickListener(v -> {
            if (startDate == 0) {
                binding.linearStartDate.setVisibility(v.VISIBLE);
                startDate = 1;
            } else {
                binding.linearStartDate.setVisibility(v.GONE);
                startDate = 0;
            }
        });

        binding.enterEndDate.setOnClickListener(v -> {
            if (endDate == 0) {
                binding.linearEndDate.setVisibility(v.VISIBLE);
                endDate = 1;
            } else {
                binding.linearEndDate.setVisibility(v.GONE);
                endDate = 0;
            }
        });

        binding.searchBtn.setOnClickListener(v -> {

            if (binding.paymentEmail.getEditText().getText().toString().equals("")) {
                name = "";
            } else {
                name = binding.paymentEmail.getEditText().getText().toString().toLowerCase().trim();
            }
            if (startDate == 1) {
                startDate = binding.startDate.getDayOfMonth();
                startMonth = binding.startDate.getMonth() + 1;
                startYear = binding.startDate.getYear();
                sDate = startMonth + "-" + startDate + "-" + startYear;
                Log.d("startDate", "setListeners: "+ sDate);
            } else {
                sDate = "";
            }


            if (endDate == 1) {
                endDate = binding.endDate.getDayOfMonth();
                endMonth = binding.endDate.getMonth() + 1;
                endYear = binding.endDate.getYear();
                eDate = endMonth + "-" + endDate + "-" + endYear;
                Log.d("endDate", "setListeners: "+ eDate);
            } else {
                eDate = "";
            }


            go(sDate, eDate, name);

            binding.linearStartDate.setVisibility(View.GONE);
            binding.linearEndDate.setVisibility(View.GONE);
            endDate = 0;
            startDate = 0;

        });


    }


    public void go(String sDate, String eDate, String name) {

        PaymentSearchModel paymentSearchModel = new PaymentSearchModel("54", Collections.EMPTY_LIST, Collections.EMPTY_LIST, "", Collections.EMPTY_LIST, "", name, "", Collections.EMPTY_LIST, Collections.EMPTY_LIST, "", "", Collections.EMPTY_LIST, sDate, eDate, "DESC", "PAY_DATE", "0", "100", "1", "international-schooling", preferenceManager.getInt(Constants.USER_ID).toString());
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<PaymentSearchModel> call = apiInterface.paymentSearch(paymentSearchModel);
        call.enqueue(new Callback<PaymentSearchModel>() {
            @Override
            public void onResponse(Call<PaymentSearchModel> call, Response<PaymentSearchModel> response) {
                if (response.body().getStatus().equals("1")) {
                    List<PaymentSearchModel.AdvancePaymentSearchResponseDTO> payment = response.body().getAdvancePaymentSearchResponseDTO();
                    PaymentAdapter paymentAdapter = new PaymentAdapter(payment);
                    binding.recyclerViewPayment.setVisibility(View.VISIBLE);
                    binding.recyclerViewPayment.setAdapter(paymentAdapter);
                    Toast.makeText(PaymentActivity.this, payment.size()+" : record found", Toast.LENGTH_SHORT).show();
                    Log.d("Response", "onResponse: "+ response.body().getAdvancePaymentSearchResponseDTO().toString());
                } else {
                    Toast.makeText(PaymentActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Response", "onResponse: "+ response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<PaymentSearchModel> call, Throwable t) {
                Log.d("Failure", "onFailure: "+ t);
            }
        });
    }
}