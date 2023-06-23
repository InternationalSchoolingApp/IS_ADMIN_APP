package org.is.isadminapp.chat;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.is.isadminapp.R;
import org.is.isadminapp.activity.WatchChatActivity;

import java.util.List;

public class ChatWatchAdapter extends RecyclerView.Adapter<ChatWatchAdapter.TeacherWatchSearchViewHolder>{


    List<WatchChatMessageModel> list;

    public ChatWatchAdapter(List<WatchChatMessageModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TeacherWatchSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assign_student_list_view, parent, false);
        ChatWatchAdapter.TeacherWatchSearchViewHolder studentSearchViewHolder = new ChatWatchAdapter.TeacherWatchSearchViewHolder(view);
        return studentSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherWatchSearchViewHolder holder, int position) {

        String studentEmail = list.get(position).studentEmail;
        String studentName = list.get(position).studentName;
        String teacherEmail = list.get(position).teacherEmail;
        holder.studentName.setText(""+studentName);
        holder.layout.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), WatchChatActivity.class);
            intent.putExtra("studentEmail",studentEmail );
            intent.putExtra("studentName",studentName );
            intent.putExtra("teacherEmail", teacherEmail);
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherWatchSearchViewHolder extends RecyclerView.ViewHolder {

        TextView teacherName, studentName, email;
        LinearLayout layout;

        public TeacherWatchSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            teacherName = itemView.findViewById(R.id.student_name);
            teacherName.setVisibility(View.GONE);
            studentName = itemView.findViewById(R.id.student_grade);
            email = itemView.findViewById(R.id.student_email);
            email.setVisibility(View.GONE);
            layout = itemView.findViewById(R.id.student_row_chat);

        }
    }

}
