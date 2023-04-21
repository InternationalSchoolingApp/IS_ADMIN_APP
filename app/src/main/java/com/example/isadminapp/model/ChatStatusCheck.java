package com.example.isadminapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatStatusCheck {



    @SerializedName("userId")
    @Expose
    private Integer userId;

        public ChatStatusCheck(Integer userId) {
        this.userId = userId;
    }

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("status")
    @Expose
    private String status;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}