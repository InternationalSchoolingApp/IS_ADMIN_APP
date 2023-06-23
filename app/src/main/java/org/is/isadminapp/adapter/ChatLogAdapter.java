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
import org.is.isadminapp.activity.NotificationViewActivity;
import org.is.isadminapp.model.AdminChatActivationLog;
import org.is.isadminapp.model.NotificationForApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ChatLogAdapter extends RecyclerView.Adapter<ChatLogAdapter.NotificationViewHolder> {
    public List<AdminChatActivationLog.Data> list ;

    public ChatLogAdapter(List<AdminChatActivationLog.Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_activity_log, parent, false);
        NotificationViewHolder notificationViewHolder = new NotificationViewHolder(view);
        return notificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        holder.startDate.setText("Start Time :"+list.get(position).getActiveTime());
        if (list.get(position).getAvailablity().equals("N")){
            holder.endDate.setText("End Time :"+list.get(position).getInactiveTime());
            String endTime = list.get(position).getInactiveTime();
            String startTime = list.get(position).getActiveTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            try {
                Date start = dateFormat.parse(startTime);
                Date end = dateFormat.parse(endTime);
                long diffInMillis = start.getTime() - end.getTime(); // difference in milliseconds
                long diffInSeconds = diffInMillis / 1000; // difference in seconds
                long hours = diffInSeconds / 3600; // hours
                long minutes = (diffInSeconds % 3600) / 60; // minutes
                long seconds = diffInSeconds % 60; // seconds
                String total = ""+hours+ ":"+minutes+":"+seconds;
                holder.time.setText(total);
            } catch (ParseException e) {
            }
        }else{
            holder.endDate.setText("Still Available");
            holder.time.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView startDate, endDate, time;
        LinearLayout layout;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            startDate = itemView.findViewById(R.id.date_last_active);
            endDate = itemView.findViewById(R.id.date_last_de_active);
            time = itemView.findViewById(R.id.total_time);
            layout = itemView.findViewById(R.id.chat_log_ll);
        }
    }
}
