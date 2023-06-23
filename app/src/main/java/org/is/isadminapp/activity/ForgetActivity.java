package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;
import org.is.isadminapp.retrofit.RetroFitClient;

import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.databinding.ActivityForgetBinding;
import org.is.isadminapp.model.ForgetPasswordModel;
import org.is.isadminapp.network.NetworkChangeListener;
import org.is.isadminapp.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetActivity extends AppCompatActivity {

    private ActivityForgetBinding binding;

    private String email;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

        binding = ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        listener();
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            email = extra.getString("email");
            binding.forgetEmailTv.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
            binding.forgetEmailTv.getEditText().setText(email);
        }

    }

    private void listener() {
        binding.forgetPageBtn.setOnClickListener(v -> {
            String username = binding.forgetEmailTv.getEditText().getText().toString().trim();

            if (username.isEmpty()) {
                binding.forgetEmailTv.setError("Email field is empty");
                binding.forgetEmailTv.setHelperText("Email field is empty");
            } else if (!username.matches(emailPattern)) {
                binding.forgetEmailTv.setHelperText("");
                binding.forgetEmailTv.setError("Invalid Mail");
            } else {
                binding.forgetEmailTv.setHelperText("");
                binding.forgetEmailTv.setError("");
                forget(username);
            }
        });
    }

    public void init() {
        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.loginAndForgetPassword(this);
    }

    private void forget(String username) {


        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        final ForgetPasswordModel forgetPasswordModel = new ForgetPasswordModel(username);
        Call<ForgetPasswordModel> call = apiInterface.forgetPassword(forgetPasswordModel);
        call.enqueue(new Callback<ForgetPasswordModel>() {
            @Override
            public void onResponse(Call<ForgetPasswordModel> call, Response<ForgetPasswordModel> response) {
                String statusValue = response.body().getStatusValue();
                String status = response.body().getStatus();
                getStatus(status, statusValue);
                Toast.makeText(ForgetActivity.this, status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ForgetPasswordModel> call, Throwable t) {

            }
        });

    }

    private void getStatus(String status, String statusValue) {

    }

}