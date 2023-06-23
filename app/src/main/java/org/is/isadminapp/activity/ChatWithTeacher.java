package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import org.is.isadminapp.adapter.RecentConversationTeacherAdapter;
import org.is.isadminapp.chat.ChatMessage;
import org.is.isadminapp.common.ColorOfStatusAndNavBar;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivityChatWithTeacherBinding;
import org.is.isadminapp.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatWithTeacher extends AppCompatActivity {
    ActivityChatWithTeacherBinding binding;


    private List<ChatMessage> conversation;
    private RecentConversationTeacherAdapter recentConversationStudentAdapter;
    private FirebaseFirestore firebaseFirestore;
    PreferenceManager preferenceManager;
    String senderIdClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatWithTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recentTeacherBackButton.setOnClickListener(v->onBackPressed());
        binding.startChatBtn.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), SearchTeacher.class);
            startActivity(intent);
            finish();
        });


        init();
        listenConversations();

    }

    public void init(){
        ColorOfStatusAndNavBar colorOfStatusAndNavBar = new ColorOfStatusAndNavBar();
        colorOfStatusAndNavBar.loginAndForgetPassword(this);
        preferenceManager = new PreferenceManager(this);
        senderIdClass= preferenceManager.getString(Constants.USER_EMAIL).toLowerCase();
        conversation = new ArrayList<>();
        recentConversationStudentAdapter = new RecentConversationTeacherAdapter(conversation);
        binding.recentRecyclerView.setAdapter(recentConversationStudentAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void listenConversations() {


        firebaseFirestore.collection("ADMIN_TEACHER_CONVERSATION")
                .whereEqualTo(Constants.KEY_SENDER_ID, preferenceManager.getString(Constants.USER_EMAIL).toLowerCase())
                .addSnapshotListener(eventListener);
        firebaseFirestore.collection("ADMIN_TEACHER_CONVERSATION")
                .whereEqualTo(Constants.KEY_RECIEVER_ID, preferenceManager.getString(Constants.USER_EMAIL).toLowerCase())
                .addSnapshotListener(eventListener);
    }


    private EventListener<QuerySnapshot> eventListener = (value , error) ->{
        if(error!=null){
            return;
        }
        if (value!= null){
            for(DocumentChange documentChange : value.getDocumentChanges()){
                if (documentChange.getType() == DocumentChange.Type.ADDED){
                    String senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                    String receiverId = documentChange.getDocument().getString(Constants.KEY_RECIEVER_ID);
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = senderId;
                    chatMessage.receiverId = receiverId;
                    if (senderId.equals(senderIdClass)){
                        chatMessage.conversionName = documentChange.getDocument().getString(Constants.KEY_RECEIVER_NAME);
                        chatMessage.conversionId = documentChange.getDocument().getString(Constants.KEY_RECIEVER_ID);
                    }else{
                        chatMessage.conversionName = documentChange.getDocument().getString(Constants.KEY_SENDER_NAME);
                        chatMessage.conversionId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                    }
                    chatMessage.message = documentChange.getDocument().getString(Constants.KEY_LAST_MESSAGE);
                    chatMessage.dateObject = documentChange.getDocument().getDate(Constants.KEY_TIME_STAMP);
                    chatMessage.teacherName = documentChange.getDocument().getString(Constants.KEY_RECEIVER_NAME);
                    chatMessage.teacherEmail  = documentChange.getDocument().getString("teacherEmail");
                    conversation.add(chatMessage);
                }else if (documentChange.getType() == DocumentChange.Type.MODIFIED){
                    for (int i = 0;i<conversation.size();i++){
                        String senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                        String receiverId = documentChange.getDocument().getString(Constants.KEY_RECIEVER_ID);
                        if(conversation.get(i).senderId.equals(senderId) && conversation.get(i).receiverId.equals(receiverId)){
                            conversation.get(i).message = documentChange.getDocument().getString(Constants.KEY_LAST_MESSAGE);
                            conversation.get(i).dateObject = documentChange.getDocument().getDate(Constants.KEY_TIME_STAMP);
                            conversation.get(i).teacherName = documentChange.getDocument().getString(Constants.KEY_RECEIVER_NAME);
                            conversation.get(i).teacherEmail = documentChange.getDocument().getString("teacherEmail");
                            break;
                        }
                    }
                }
            }
            Collections.sort(conversation, (obj1, obj2)-> obj2.dateObject.compareTo(obj1.dateObject));
            recentConversationStudentAdapter.notifyDataSetChanged();
            binding.recentRecyclerView.smoothScrollToPosition(0);
            binding.recentRecyclerView.setVisibility(View.VISIBLE);

        }
    };

}