package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;


import org.is.isadminapp.fragment.Chat;
import org.is.isadminapp.fragment.Dashboard;
import org.is.isadminapp.fragment.More;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.is.isadminapp.R;
import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.databinding.ActivityDashboardBinding;
import org.is.isadminapp.network.NetworkChangeListener;


public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
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

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this , MailViewNotification.class);
                startActivity(i);
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
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), new Dashboard()).commit();
        binding.bottomNavMenu.setItemSelected(R.id.nav_dashboard, true);

        bottomMenu();


    }

    private void init(){
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