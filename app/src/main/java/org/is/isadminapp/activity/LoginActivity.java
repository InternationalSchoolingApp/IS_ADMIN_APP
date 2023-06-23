package org.is.isadminapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.is.isadminapp.retrofit.RetroFitClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.common.MobileModel;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivityLoginBinding;
import org.is.isadminapp.model.FirebaseTokenModel;
import org.is.isadminapp.model.LoginModel;
import org.is.isadminapp.network.NetworkChangeListener;
import org.is.isadminapp.preference.PreferenceManager;
import org.is.isadminapp.retrofit.ApiInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private FirebaseFirestore database;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private long pressedTime;

    private String model;

    PreferenceManager preferenceManager;

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

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        listeners();

    }

    private void init() {
        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.loginAndForgetPassword(this);
        database = FirebaseFirestore.getInstance();
        preferenceManager = new PreferenceManager(this);
        MobileModel mobileModel = new MobileModel();
        model = mobileModel.getDeviceName();

    }

    private void listeners() {
        binding.forgetBtn.setOnClickListener(v -> {
            if (binding.username.getEditText().getText().toString() != null) {
                Intent intent = new Intent(v.getContext(), ForgetActivity.class);
                intent.putExtra("email", binding.username.getEditText().getText().toString());
                startActivity(intent);
            } else {
                Intent i = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(i);
            }
        });
        binding.loginBtn.setOnClickListener(c -> {
            check();
        });
    }

    private void check() {

        String username = binding.username.getEditText().getText().toString().trim();
        String password = binding.password.getEditText().getText().toString().trim();

        if (username.isEmpty()) {
            binding.username.setError("Email field is empty");
            binding.username.setHelperText("Email field is empty");
        } else if (password.isEmpty()) {
            binding.password.setError("Password is empty");
            binding.password.setHelperText("Password field is empty");
        } else if (!username.matches(emailPattern)) {
            binding.username.setHelperText("");
            binding.username.setError("Invalid Mail");
        } else {
            binding.username.setHelperText("");
            binding.username.setError("");
            binding.password.setHelperText("");
            binding.password.setError("");
            finalDone();
        }

    }

    private void finalDone() {

        String email = binding.username.getEditText().getText().toString().toLowerCase().trim().toLowerCase();
        String password = binding.password.getEditText().getText().toString().trim();

        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);

        final LoginModel loginModel = new LoginModel(email, password, model, 1);
        Call<LoginModel> call = apiInterface.loginPostData(loginModel);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                String status = response.body().getStatus();
                if (status.equals("success")) {
                    int userIdOnResponse = response.body().getUserId();
                    Log.d("LOGIN ID", "onResponse: "+ userIdOnResponse);
                    int id = response.body().getPlatformId();
                    String role = response.body().getRole();

                    String name = response.body().getName();
                    preferenceManager.putString(Constants.NAME, name);
                    preferenceManager.putBoolean(Constants.USER_LOGGED, true);
                    preferenceManager.putInt(Constants.USER_ID, userIdOnResponse);
                    preferenceManager.putString(Constants.USER_EMAIL, email);
                    preferenceManager.putInt(Constants.PLATFORM_ID, id);
                    preferenceManager.putString(Constants.ROLE, role);

                    saveTokenFireBase(userIdOnResponse);
                    registerInFirebase(email, name, userIdOnResponse);
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    // getStatus(status);
                    Toast.makeText(LoginActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void registerInFirebase(String usermail, String name, Integer userId) {
        String mail = usermail.toLowerCase();
        if (!firebaseCheck(mail)) {
            CollectionReference user = database.collection(Constants.FIREBASE_USER_DB);
            HashMap<String, Object> data = new HashMap<>();
            data.put(Constants.NAME, name);
            data.put(Constants.USER_EMAIL, mail);
            data.put(Constants.USER_ID, userId);
            data.put(Constants.ROLE, Constants.ADMIN_ROLE);
            data.put(Constants.FIREBASE_TOKEN, preferenceManager.getString(Constants.FIREBASE_TOKEN));
            user.document(mail).set(data).addOnSuccessListener(d -> {

            }).addOnFailureListener(exception -> {

            });
        }

    }



    public Boolean firebaseCheck(String email) {
        Integer i[] = {0};

        database = FirebaseFirestore.getInstance();
        DocumentReference docRef = database.collection(Constants.FIREBASE_USER_DB).document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().getData() != null) {
                        docRef.update("firebaseToken", preferenceManager.getString(Constants.FIREBASE_TOKEN));
                        i[0] = 1;

                        Log.d("FCM User", "Exist : " + task.getResult().getData());
                    } else {
                        Log.d("FCM User", "Not Found");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

        if (i[0] == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press Back Again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    public void saveTokenFireBase(Integer userId) {
        MobileModel mobileModel = new MobileModel();
        mobileModel.getDeviceName();
        String token = preferenceManager.getString(Constants.FIREBASE_TOKEN);
        FirebaseTokenModel firebaseTokenModel = new FirebaseTokenModel(userId, mobileModel.getDeviceName(), token);
        Log.d("Jaate Hue", "saveTokenFireBase: "+userId +" "+mobileModel.getDeviceName()+" "+token);
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<FirebaseTokenModel> call = apiInterface.firebaseToken(firebaseTokenModel);
        call.enqueue(new Callback<FirebaseTokenModel>() {
            @Override
            public void onResponse(Call<FirebaseTokenModel> call, Response<FirebaseTokenModel> response) {

                Log.d("TOKEN RESPONSE", "onResponse: " + response.body().getStatus());
                if (response.body().getToken() == null) {

                }
            }

            @Override
            public void onFailure(Call<FirebaseTokenModel> call, Throwable t) {
                Log.d("TOKEN FAILED RESPONSE", "onResponse: " + t.toString());


            }
        });

    }


}