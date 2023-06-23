package org.is.isadminapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.is.isadminapp.R;
import org.is.isadminapp.activity.ChatResultActivity;
import org.is.isadminapp.model.ChatWithTeacherModel;

import java.util.List;

public class WatchTeacherSearchAdapter extends RecyclerView.Adapter<WatchTeacherSearchAdapter.ChatTeacherSearchViewHolder> {

    List<ChatWithTeacherModel.ApprovedTeacher> list;

    public WatchTeacherSearchAdapter(List<ChatWithTeacherModel.ApprovedTeacher> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChatTeacherSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assign_student_list_view, parent, false);
        WatchTeacherSearchAdapter.ChatTeacherSearchViewHolder chatTeacherSearchViewHolder = new WatchTeacherSearchAdapter.ChatTeacherSearchViewHolder(view);
        return chatTeacherSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatTeacherSearchViewHolder holder, int position) {

        String email = list.get(position).getEmailId();
        holder.name.setText(""+list.get(position).getUserName());
        holder.email.setText(""+email);


        holder.layout.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChatResultActivity.class);
            intent.putExtra("email", email);
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
