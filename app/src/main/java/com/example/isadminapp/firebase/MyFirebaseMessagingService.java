package com.example.isadminapp.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.preference.PreferenceManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

     PreferenceManager preferenceManager;

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        preferenceManager = new PreferenceManager(getApplicationContext());
        preferenceManager.putString(Constants.FIREBASE_TOKEN, token);
        Log.d("TOKEN", "onNewToken: "+ token);
    }



    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

    }


}
