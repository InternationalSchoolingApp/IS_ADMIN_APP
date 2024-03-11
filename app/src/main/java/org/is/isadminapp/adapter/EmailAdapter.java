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
import org.is.isadminapp.activity.FullscreenActivity;
import org.is.isadminapp.activity.NotificationViewActivity;
import org.is.isadminapp.model.NotificationForApp;
import org.is.isadminapp.model.NotificationModel;

import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder>{

    public List<NotificationModel> list ;
    public EmailAdapter(List<NotificationModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.notification_view, parent, false);
        EmailAdapter.EmailViewHolder notificationViewHolder = new EmailAdapter.EmailViewHolder(view);
        return notificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        String title = list.get(position).getTitle();
        String message = list.get(position).getSubtitle();
        String time = list.get(position).getTimestamp();
        String body = list.get(position).getNotificationId();

        holder.title.setText(title);
        holder.message.setText(message);
        holder.time.setText(time);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FullscreenActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("subtitle", message);
                intent.putExtra("time", time);
                intent.putExtra("body", body);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmailViewHolder extends RecyclerView.ViewHolder {

        TextView title, message, time;
        LinearLayout layout;
        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notification_title);
            message = itemView.findViewById(R.id.notification_message);
            time = itemView.findViewById(R.id.date_notification);
            layout = itemView.findViewById(R.id.complete_notification_field);
        }
    }
}
