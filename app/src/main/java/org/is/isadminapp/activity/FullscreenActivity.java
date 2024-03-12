package org.is.isadminapp.activity;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.databinding.ActivityFullscreenBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FullscreenActivity extends AppCompatActivity {

    private ActivityFullscreenBinding binding;
    private String notificationId;
    private String title;
    private String subtitle;
    private String body;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.colorOfStatusBar(this);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            notificationId = extra.getString("notificationId");
            binding.notificationBackButton.setOnClickListener(v->{
                onBackPressed();
            });
            title = extra.getString("title");
            subtitle = extra.getString("subtitle");
            body = extra.getString("body");
            try {
                String time = extra.getString("time");
                SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                Date date = inputFormat.parse(time);
                SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss aa");
                String formattedDate = outputFormat.format(date);
                binding.date.setText(formattedDate);
                System.out.println(formattedDate);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle the parsing exception as needed
            }

            binding.title.setText(title);
            binding.subtitle.setText(subtitle);

            binding.body.loadDataWithBaseURL(null, "<html><body><div>" + body + "</div></body></html>", "text/html; charset=utf-8", "UTF-8", null);
            Log.d("Notification Id", "onCreate: "+ notificationId);
        }
    }
}