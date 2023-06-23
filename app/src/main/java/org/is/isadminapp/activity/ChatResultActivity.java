package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.is.isadminapp.chat.ChatWatchAdapter;
import org.is.isadminapp.chat.WatchChatMessageModel;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.databinding.ActivityChatResultBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
public class ChatResultActivity extends AppCompatActivity {

    private ActivityChatResultBinding binding;
    String teacherEmail;

    private ChatWatchAdapter chatAdapter;

    private List<WatchChatMessageModel> chatMessages;

    private FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.notificationBackButton.setOnClickListener(v->onBackPressed());

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            teacherEmail = extra.getString("email").toLowerCase();
        }
        init();
        listenChats();


    }

    private void init() {

        chatMessages = new ArrayList<>();
        database = FirebaseFirestore.getInstance();
        chatAdapter = new ChatWatchAdapter(chatMessages);
        binding.chatRecyclerView.setAdapter(chatAdapter);

    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            int count = chatMessages.size();
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    WatchChatMessageModel chatMessage = new WatchChatMessageModel();
                    chatMessage.teacherEmail = documentChange.getDocument().getString("teacherEmail");
                    chatMessage.studentEmail = documentChange.getDocument().getString("studentEmail");
                    chatMessage.studentName = documentChange.getDocument().getString("studentName");
                    chatMessages.add(chatMessage);
                }
            }
            if (count != 0) {
                chatAdapter.notifyItemRangeInserted(chatMessages.size(), chatMessages.size());
            }else{
                Toast.makeText(this, "No Chat Found Till Now", Toast.LENGTH_SHORT).show();
            }
        }

    };

    private void listenChats() {
        database.collection(Constants.KEY_COLLECTIONS_CONVERSATION)
                .whereEqualTo("teacherEmail", teacherEmail)
                .addSnapshotListener(eventListener);
    }

}