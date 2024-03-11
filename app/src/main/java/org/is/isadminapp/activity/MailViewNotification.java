package org.is.isadminapp.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.is.isadminapp.adapter.EmailAdapter;
import org.is.isadminapp.adapter.NotificationAdapter;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivityMailViewNotificationBinding;
import org.is.isadminapp.model.NotificationModel;
import org.is.isadminapp.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailViewNotification extends AppCompatActivity {


    private ActivityMailViewNotificationBinding binding;
    private PreferenceManager preferenceManager;
    private List<NotificationModel> notificationList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMailViewNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(this);
        notificationList = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        binding.emailList.setLayoutManager(llm);
        binding.notificationBackButton.setOnClickListener(v->{
            onBackPressed();
        });
        listenNotifications();






    }

    private void listenNotifications(){

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        Query query = database.collection("NOTIFICATIONS")
                .where(Filter.or(
                        Filter.equalTo("for", "ADMIN"),
                        Filter.arrayContains("userId", preferenceManager.getInt(Constants.USER_ID))
                ))
                .orderBy("timeStamp", Query.Direction.DESCENDING);

        query.addSnapshotListener((value, error) -> {
            if (error != null) {
                // Handle error
                Log.e("Firestore Error", "Error getting notifications", error);
                return;
            }

            // Check if the query result is not null
            if (value != null) {
                // Clear the existing list before updating with new data
                notificationList.clear();

                // Iterate through the documents in the query result
                for (DocumentSnapshot document : value.getDocuments()) {
                    String subtitle = document.getString("subtitle");
                    String title = document.getString("title");
                    Date timestamp = document.getDate("timeStamp");
                    String body = document.getString("body");
                    Log.d("TAG", "listenNotifications: "+ title + subtitle + timestamp);
                    NotificationModel notification = new NotificationModel(title, subtitle, timestamp.toString(), body );
                    notificationList.add(notification);
                }
                EmailAdapter notificationAdapter = new EmailAdapter(notificationList);
                binding.emailList.setAdapter(notificationAdapter);
            }
        });


    }



}

