package com.example.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.isadminapp.chat.ChatAdapter;
import com.example.isadminapp.chat.ChatMessage;
import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.databinding.ActivityWatchChatBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WatchChatActivity extends AppCompatActivity {

    private String conversionId = null;
    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;
    private FirebaseFirestore database;
    ActivityWatchChatBinding binding;
    String teacherEmail, studentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWatchChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        loadRecieverDetails();
        listenMessage();

    }

    public void init() {

        binding.backButtonChat.setOnClickListener(v->{
            onBackPressed();
        });
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            studentEmail = extra.getString("studentEmail").toLowerCase();
            teacherEmail = extra.getString("teacherEmail").toLowerCase();
        }

        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages, studentEmail );
        database = FirebaseFirestore.getInstance();
        binding.chatContent.setAdapter(chatAdapter);
    }

    private void loadRecieverDetails() {
        binding.chatScreenTeacherName.setText(studentEmail);
        binding.chatTeacherSubjectName.setText(teacherEmail);
    }

    private String getReadableDateTime(Date date) {
        return new SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
    }

    private void listenMessage() {
        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, studentEmail)
                .whereEqualTo(Constants.KEY_RECIEVER_ID, teacherEmail)
                .addSnapshotListener(eventListener);
        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, teacherEmail)
                .whereEqualTo(Constants.KEY_RECIEVER_ID, studentEmail)
                .addSnapshotListener(eventListener);
    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            int count = chatMessages.size();
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                    chatMessage.receiverId = documentChange.getDocument().getString(Constants.KEY_RECIEVER_ID);
                    chatMessage.message = documentChange.getDocument().getString(Constants.KEY_MESSAGE);
                    chatMessage.time = getReadableDateTime(documentChange.getDocument().getDate(Constants.KEY_TIME_STAMP));
                    chatMessage.dateObject = documentChange.getDocument().getDate(Constants.KEY_TIME_STAMP);
                    chatMessages.add(chatMessage);


                }
            }
            Collections.sort(chatMessages, (obj1, obj2) -> obj1.dateObject.compareTo(obj2.dateObject));
            if (count == 0) {
                chatAdapter.notifyDataSetChanged();

            } else {
                    chatAdapter.notifyItemRangeInserted(chatMessages.size(), chatMessages.size());
                    binding.chatContent.smoothScrollToPosition(chatMessages.size() - 1);
            }
            binding.chatContent.setVisibility(View.VISIBLE);
        }

        if (conversionId == null) {
            checkForConversion();
        }
    };

    private final OnCompleteListener<QuerySnapshot> conversionCompleteListener = task -> {
        if (task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0) {
            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
            conversionId = documentSnapshot.getId();
        }
    };

    private void checkForConversion() {
        if (chatMessages.size() != 0) {
            checkForConversionRemotely(studentEmail, teacherEmail);
            checkForConversionRemotely(teacherEmail, studentEmail);
        }
    }

    private void checkForConversionRemotely(String senderId, String receiverId) {
        Task<QuerySnapshot> querySnapshotTask = database.collection(Constants.KEY_COLLECTIONS_CONVERSATION).whereEqualTo(Constants.KEY_SENDER_ID, senderId).whereEqualTo(Constants.KEY_RECIEVER_ID, receiverId).get().addOnCompleteListener(conversionCompleteListener);
    }

}