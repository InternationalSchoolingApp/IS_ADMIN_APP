package org.is.isadminapp.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.common.DateUtil;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivityFullscreenBinding;
import org.is.isadminapp.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FullscreenActivity extends AppCompatActivity {

    private ActivityFullscreenBinding binding;
    private String notificationId;
    private String title;
    private String subtitle;
    private String body;

    private FirebaseFirestore database;

    PreferenceManager preferenceManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullscreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseFirestore.getInstance();
        preferenceManager = new PreferenceManager(this);

        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.colorOfStatusBar(this);
        Bundle extra = getIntent().getExtras();
        PreferenceManager preferenceManager = new PreferenceManager(this);
        if (extra != null) {
            notificationId = extra.getString("notificationId");
            binding.notificationBackButton.setOnClickListener(v -> {
                onBackPressed();
            });

            String userId = preferenceManager.getString(Constants.USER_EMAIL);

            CollectionReference notificationsCollection = database.collection(Constants.NOTIFICATIONS);

            Query query = notificationsCollection.whereEqualTo("notificationId", notificationId);

            query.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("TAG", "Query successful");
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot.isEmpty()) {
                        Log.d("TAG", "No documents found matching the query criteria");
                    } else {
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            // Get the document reference
                            DocumentReference docRef = document.getReference();

                            // Get the current value of seenBy array
                            List<Integer> seenByList = document.contains("unSeenBy") ? (List<Integer>) document.get("unSeenBy") : null;

                            Log.d("TAG", "Existing seenBy list: " + seenByList);

                            // Add userId to the seenByList if it's not already present
                            if (seenByList == null) {
                                seenByList = new ArrayList<>();
                            }
                            if (seenByList.contains(userId)) {
                                seenByList.remove(userId);
                                Log.d("TAG", "userId added to seenBy list: " + userId);
                            } else {
                                Log.d("TAG", "userId not exists in seenBy list: " + userId);
                            }

                            // Update the document with the modified seenBy array
                            docRef.update("unSeenBy", seenByList)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("TAG", "Document updated successfully");
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("TAG", "Error updating document: " + e.getMessage());
                                    });
                        }
                    }
                } else {
                    Log.e("TAG", "Query failed: " + task.getException());
                    // Handle failures
                }
            });


            title = extra.getString("title");
            subtitle = extra.getString("subtitle");
            body = extra.getString("body");

            String time = extra.getString("time");
            Date date = DateUtil.offsetTimeZone(new Date(time), "Asia/Singapore", preferenceManager.getString(Constants.TIMEZONE));
            binding.date.setText(date.toString());

            binding.title.setText(title);
            binding.subtitle.setText(subtitle);

            binding.body.loadDataWithBaseURL(null, "<html><body><div>" + body + "</div></body></html>", "text/html; charset=utf-8", "UTF-8", null);
            markAsSeen();
            Log.d("Notification Id", "onCreate: " + notificationId);
        }
    }


    static void markAsSeen(){

    }

}