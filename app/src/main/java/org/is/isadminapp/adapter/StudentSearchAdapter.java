package org.is.isadminapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.is.isadminapp.R;
import org.is.isadminapp.model.AddPaymentStudentSearchModel;

import java.util.List;

public class StudentSearchAdapter extends RecyclerView.Adapter<StudentSearchAdapter.StudentSearchViewHolder> {


    List<AddPaymentStudentSearchModel.List> list ;

    public StudentSearchAdapter(List<AddPaymentStudentSearchModel.List> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StudentSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assign_student_list_view, parent, false);
        StudentSearchAdapter.StudentSearchViewHolder studentSearchViewHolder = new StudentSearchAdapter.StudentSearchViewHolder(view);
        return studentSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentSearchViewHolder holder, int position) {

        holder.name.setText(""+list.get(position).getFullName());
        holder.grade.setText(""+list.get(position).getStandard());
        holder.email.setText(""+list.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StudentSearchViewHolder extends RecyclerView.ViewHolder {

        TextView name, grade, email;

        LinearLayout layout;

        public StudentSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            grade = itemView.findViewById(R.id.student_grade);
            email = itemView.findViewById(R.id.student_email);
            layout = itemView.findViewById(R.id.student_row_chat);

        }
    }
}
