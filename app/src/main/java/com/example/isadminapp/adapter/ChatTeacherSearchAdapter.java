package com.example.isadminapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isadminapp.R;
import com.example.isadminapp.activity.ChatTeacherActivity;
import com.example.isadminapp.model.ChatWithTeacherModel;

import java.util.List;

public class ChatTeacherSearchAdapter extends RecyclerView.Adapter<ChatTeacherSearchAdapter.ChatTeacherSearchViewHolder> {

    List<ChatWithTeacherModel.ApprovedTeacher> list;

    public ChatTeacherSearchAdapter(List<ChatWithTeacherModel.ApprovedTeacher> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChatTeacherSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assign_student_list_view, parent, false);
        ChatTeacherSearchAdapter.ChatTeacherSearchViewHolder chatTeacherSearchViewHolder = new ChatTeacherSearchAdapter.ChatTeacherSearchViewHolder(view);
        return chatTeacherSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatTeacherSearchViewHolder holder, int position) {
        holder.name.setText(""+list.get(position).getUserName());
        //holder.grade.setText(""+list.get(position).getEmailId());
        holder.email.setText(""+list.get(position).getEmailId());
        int id = list.get(position).getId();
        String name = list.get(position).getUserName();
        String email = list.get(position).getEmailId();

        holder.layout.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChatTeacherActivity.class);
            intent.putExtra("userId",id );
            intent.putExtra("email", email);
            intent.putExtra("name", name);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChatTeacherSearchViewHolder extends RecyclerView.ViewHolder{

    TextView name, grade, email;

    LinearLayout layout;

    public ChatTeacherSearchViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.student_name);
        grade = itemView.findViewById(R.id.student_grade);
        grade.setVisibility(View.GONE);
        email = itemView.findViewById(R.id.student_email);
        layout = itemView.findViewById(R.id.student_row_chat);
    }
}
}
