package org.is.isadminapp.firebase;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.is.isadminapp.R;
import org.is.isadminapp.activity.ChatWithStudent;
import org.is.isadminapp.activity.ChatWithTeacher;
import org.is.isadminapp.activity.FullscreenActivity;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.model.User;
import org.is.isadminapp.preference.PreferenceManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    PreferenceManager preferenceManager;

    FirebaseFirestore firebaseFirestore;


    private void updateFireBaseToken(String token) {

        if (token != null && !token.equals("")) {
            preferenceManager.putString(Constants.FIREBASE_TOKEN, token);
            DocumentReference docRef = firebaseFirestore.collection(Constants.FIREBASE_USER_DB).document(token);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    docRef.update("firebaseToken", preferenceManager.getString(Constants.FIREBASE_TOKEN));
                }
            });
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        firebaseFirestore = FirebaseFirestore.getInstance();
        preferenceManager = new PreferenceManager(getApplicationContext());
        updateFireBaseToken(token);
    }





    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

        super.onMessageReceived(message);
        Log.d("MESSAGE", "onMessageReceived: " + (message.getData()));

        String channel = message.getData().get("channel");
        String channelinmessage = message.getData().get("channel");

        if (channel != null && channel.equals("General")) {
            String nId = message.getData().get("notificationId");
            String subtitle = message.getData().get("subtitle");
            String title = message.getData().get("title");
            String body = message.getData().get("body");
            String time = message.getData().get("time");
            Log.d("nId", "onMessageReceived: " + nId);
            int notificationId = new Random().nextInt();
            Intent intent = new Intent(this, FullscreenActivity.class);
            intent.putExtra("notificationId", nId);
            intent.putExtra("title", title);
            intent.putExtra("subtitle", subtitle);
            intent.putExtra("body", body);
            intent.putExtra("time", time);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setSmallIcon(R.drawable.bellnotify);
            builder.setContentTitle(title);
            builder.setContentText(subtitle);
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message.getData().get("title")));
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            CharSequence channelName = "General Message";
            String channelDescription = "This notification channel used for general message";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channel, channelName, importance);
            notificationChannel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            notificationManagerCompat.notify(notificationId, builder.build());
        } else if(channelinmessage.equals("TEACHER_ADMIN")){
            User user = new User();
            user.id = message.getData().get(Constants.USER_EMAIL);
            user.name = message.getData().get(Constants.NAME);
            user.token = message.getData().get(Constants.FIREBASE_TOKEN);

            int notificationId = new Random().nextInt();
            String channelId = "chat_message";

            Intent intent = new Intent(this, ChatWithTeacher.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
            builder.setSmallIcon(R.drawable.bellnotify);
            builder.setContentTitle(user.name);
            builder.setContentText(message.getData().get(Constants.KEY_MESSAGE));
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message.getData().get(Constants.KEY_MESSAGE)));
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            CharSequence channelName = "Chat Message";
            String channelDescription = "This notification channel used for chat message";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel c = new NotificationChannel(channelId, channelName, importance);
            c.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(c);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            notificationManagerCompat.notify(notificationId, builder.build());
        } else if (channelinmessage.equals("STUDENT_ADMIN")) {
            User user = new User();
            user.id = message.getData().get(Constants.USER_EMAIL);
            user.name = message.getData().get(Constants.NAME);
            user.token = message.getData().get(Constants.FIREBASE_TOKEN);

            int notificationId = new Random().nextInt();
            String channelId = "chat_message";

            Intent intent = new Intent(this, ChatWithStudent.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
            builder.setSmallIcon(R.drawable.bellnotify);
            builder.setContentTitle(user.name);
            builder.setContentText(message.getData().get(Constants.KEY_MESSAGE));
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message.getData().get(Constants.KEY_MESSAGE)));
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);

            CharSequence channelName = "Chat Message";
            String channelDescription = "This notification channel used for chat message";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel c = new NotificationChannel(channelId, channelName, importance);
            c.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(c);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            notificationManagerCompat.notify(notificationId, builder.build());
        }
    }


}
