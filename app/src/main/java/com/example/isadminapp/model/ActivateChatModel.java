package com.example.isadminapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivateChatModel {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("status")
    @Expose
    private String status;

    public ActivateChatModel(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
