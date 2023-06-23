package org.is.isadminapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.is.isadminapp.R;
import org.is.isadminapp.activity.ChatStudentActivity;
import org.is.isadminapp.activity.SearchStudentForChatActivity;
import org.is.isadminapp.model.StudentAvailablityForChatModel;

import java.util.List;

public class StudentSearchChatAdapter extends RecyclerView.Adapter<StudentSearchChatAdapter.ChatStudentSearchViewHolder>{


    List<StudentAvailablityForChatModel.Student> list;

    public StudentSearchChatAdapter(List<StudentAvailablityForChatModel.Student> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ChatStudentSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assign_student_list_view, parent, false);
        StudentSearchChatAdapter.ChatStudentSearchViewHolder chatStudentSearchViewHolder = new StudentSearchChatAdapter.ChatStudentSearchViewHolder(view);
        return chatStudentSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatStudentSearchViewHolder holder, int position) {
        holder.name.setText(""+list.get(position).getUserName());
        holder.grade.setText(""+list.get(position).getGrade().replace("-", " "));
        holder.email.setText(""+list.get(position).getEmailId());
        Integer id = list.get(position).getId();
        String name = list.get(position).getUserName();
        String email = list.get(position).getEmailId();
        holder.layout.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChatStudentActivity.class);
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

    public class ChatStudentSearchViewHolder extends RecyclerView.ViewHolder{

        TextView name, grade, email;

        LinearLayout layout;

        public ChatStudentSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            grade = itemView.findViewById(R.id.student_grade);
            email = itemView.findViewById(R.id.student_email);
            layout = itemView.findViewById(R.id.student_row_chat);
        }
    }
}
