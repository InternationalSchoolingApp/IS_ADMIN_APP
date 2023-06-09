package org.is.isadminapp.chat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.is.isadminapp.databinding.ItemContainerRecieveMessageBinding;
import org.is.isadminapp.databinding.ItemContainerSentMessageBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ChatMessage> chatMessages;
    private final String senderId;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECIEVE = 2;

    public ChatAdapter(List<ChatMessage> chatMessages, String senderId) {
        this.chatMessages = chatMessages;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT){
            return new SendMessageViewHolder(
                    ItemContainerSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,false
                    )
            );
        }else{
            return new RecieveMessageViewHolder(
                    ItemContainerRecieveMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent, false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) ==  VIEW_TYPE_SENT){
            ((SendMessageViewHolder) holder).setData(chatMessages.get(position));
        }else{
            ((RecieveMessageViewHolder) holder).setData(chatMessages.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).senderId.equals(senderId)){
        return VIEW_TYPE_SENT;
        }else{
            return VIEW_TYPE_RECIEVE;
        }
    }

    static class SendMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerSentMessageBinding binding;

        public SendMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.time);
        }
    }
    static  class RecieveMessageViewHolder extends RecyclerView.ViewHolder{

        private final ItemContainerRecieveMessageBinding binding;

        public RecieveMessageViewHolder(ItemContainerRecieveMessageBinding itemContainerRecieveMessageBinding){
            super(itemContainerRecieveMessageBinding.getRoot());
            binding = itemContainerRecieveMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            binding.textMessageRecieve.setText(chatMessage.message);
            binding.textMessageRecieveDateTime.setText(chatMessage.time);
        }
    }
}
