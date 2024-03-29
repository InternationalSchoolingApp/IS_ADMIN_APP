package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;


import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.fragment.Chat;
import org.is.isadminapp.fragment.Dashboard;
import org.is.isadminapp.fragment.More;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.is.isadminapp.R;
import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.databinding.ActivityDashboardBinding;
import org.is.isadminapp.network.NetworkChangeListener;
import org.is.isadminapp.preference.PreferenceManager;
import org.is.isadminapp.retrofit.ApiInterface;
import org.is.isadminapp.retrofit.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    PreferenceManager preferenceManager;

    FirebaseFirestore firestore ;


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


    void mailCount(){
        firestore = FirebaseFirestore.getInstance();
        String userId = preferenceManager.getString(Constants.USER_EMAIL);

        CollectionReference notificationsCollection = firestore.collection(Constants.NOTIFICATIONS);

        Query query = notificationsCollection
                .where(Filter.arrayContains("unSeenBy", userId));

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    int notificationCount = querySnapshot.size();
                    binding.mailCount.setText(String.valueOf(notificationCount)); // Convert int to String
                } else {
                    // Handle if querySnapshot is null
                }
            } else {
                // Handle task failure
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this , MailViewNotification.class);
                startActivity(i);
                finish();
            }
        });

        binding.notificationBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this , NotificationActivity.class);
                startActivity(i);
            }
        });

        init();
        mailCount();
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), new Dashboard()).commit();
        binding.bottomNavMenu.setItemSelected(R.id.nav_dashboard, true);

        bottomMenu();

        updateTimeZone();


    }

    private void updateTimeZone() {
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<String> call = apiInterface.getCurrentTimeZone(preferenceManager.getInt(Constants.USER_ID));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String timeZone = response.body();
                preferenceManager.putString(Constants.TIMEZONE, timeZone);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                preferenceManager.putString(Constants.TIMEZONE, "Asia/Singapore");
            }
        });
    }

    private void init(){
        preferenceManager = new PreferenceManager(this);
        ColorOfStatusAndNavBar color = new ColorOfStatusAndNavBar();
        color.dashboard(this);
    }

    private void bottomMenu() {

        binding.bottomNavMenu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.nav_dashboard:
                        fragment = new Dashboard();
                        break;
                    case R.id.nav_chat:
                        fragment = new Chat();
                        break;
                    case R.id.nav_more:
                        fragment = new More();

                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });

    }

}