package com.example.isadminapp.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isadminapp.R;
import com.example.isadminapp.activity.AdminProfileViewActivity;
import com.example.isadminapp.model.ManageUserSearchModel;

import java.util.List;

public class ManageUserSearchAdapter extends RecyclerView.Adapter<ManageUserSearchAdapter.ManageUserSearchViewHolder>{

    private java.util.List<ManageUserSearchModel.List> list ;

    public ManageUserSearchAdapter(List<ManageUserSearchModel.List> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ManageUserSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assign_student_list_view, parent, false);
        ManageUserSearchAdapter.ManageUserSearchViewHolder manageUserSearchViewHolder = new ManageUserSearchAdapter.ManageUserSearchViewHolder(view);
        return manageUserSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ManageUserSearchViewHolder holder, int position) {

        holder.name.setText(""+list.get(position).getUserFullName());
        holder.userRole.setText(""+list.get(position).getParentUserRole());
        holder.email.setText(""+list.get(position).getEmail());

        String userId = String.valueOf(list.get(position).getId());

        holder.layout.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), AdminProfileViewActivity.class);
            intent.putExtra("userId", userId );
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ManageUserSearchViewHolder extends RecyclerView.ViewHolder {

        TextView name, userRole, email;

        LinearLayout layout;

        public ManageUserSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            userRole = itemView.findViewById(R.id.student_grade);
            email = itemView.findViewById(R.id.student_email);
            layout = itemView.findViewById(R.id.student_row_chat);

        }
    }
}
