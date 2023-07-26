package org.is.isadminapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.is.isadminapp.activity.ChatStudentActivity;
import org.is.isadminapp.chat.ChatMessage;
import org.is.isadminapp.databinding.TeacherRecentConversationBinding;

import java.util.List;

public class RecentConversationStudentAdapter extends RecyclerView.Adapter<RecentConversationStudentAdapter.ConversionViewHolder> {

    private final List<ChatMessage> chatMessages;

    public RecentConversationStudentAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(TeacherRecentConversationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        holder.setData(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    class ConversionViewHolder extends RecyclerView.ViewHolder {
        TeacherRecentConversationBinding binding;

        ConversionViewHolder(TeacherRecentConversationBinding teacherRecentConversationBinding) {
            super(teacherRecentConversationBinding.getRoot());
            binding = teacherRecentConversationBinding;
        }

        void setData(ChatMessage chatMessage) {
            binding.recentTeacherNameChat.setText(chatMessage.studentEmail);
            binding.teacherRecentMessage.setText(chatMessage.message);
            binding.timeRecentMessage.setText(chatMessage.dateObject.toString());
            String studentId = chatMessage.studentId;
            String studentName = chatMessage.studentName;
            String studentEmail = chatMessage.studentEmail;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {

                                                         Intent intent = new Intent(v.getContext(), ChatStudentActivity.class);
                                                         intent.putExtra("userId", studentId);
                                                         intent.putExtra("name", studentName);
                                                         intent.putExtra("email", studentEmail);
                                                         v.getContext().startActivity(intent);

                                                     }
                                                 }


            );


        }
    }

}
