package org.is.isadminapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.is.isadminapp.R;
import org.is.isadminapp.model.AdminProfileViewModel;

import java.util.List;

public class SelectedCourseAdapter extends RecyclerView.Adapter<SelectedCourseAdapter.SelectedCourseViewHolder>{


    List<AdminProfileViewModel.List> list ;

    public SelectedCourseAdapter(List<AdminProfileViewModel.List> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SelectedCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.assign_student_list_view, parent, false);
        SelectedCourseAdapter.SelectedCourseViewHolder studentSearchViewHolder = new SelectedCourseAdapter.SelectedCourseViewHolder(view);
        return studentSearchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedCourseViewHolder holder, int position) {

        holder.name.setText(""+list.get(position).getSubjectTitle());
        holder.code.setText(""+list.get(position).getSubjectCode());
        holder.extra.setText("Course Type :"+list.get(position).getCourseType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SelectedCourseViewHolder extends RecyclerView.ViewHolder {

        TextView name, code, extra;

        LinearLayout layout;

        public SelectedCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            code = itemView.findViewById(R.id.student_grade);
            extra = itemView.findViewById(R.id.student_email);
            layout = itemView.findViewById(R.id.student_row_chat);

        }
    }
}
