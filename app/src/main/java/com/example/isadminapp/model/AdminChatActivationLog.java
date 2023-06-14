package com.example.isadminapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminChatActivationLog {

    @SerializedName("data")
    @Expose
    private List<Data> data;
    @SerializedName("status")
    @Expose
    private String status;

    public AdminChatActivationLog(Integer userId) {
        this.userId = userId;
    }

    @SerializedName("userId")
    @Expose
    private Integer userId;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Data {

        @SerializedName("activeTime")
        @Expose
        private String activeTime;
        @SerializedName("availablity")
        @Expose
        private String availablity;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userId")
        @Expose
        private Integer userId;


        @SerializedName("inactiveTime")
        @Expose
        private String inactiveTime;

        public String getInactiveTime() {
            return inactiveTime;
        }

        public void setInactiveTime(String inactiveTime) {
            this.inactiveTime = inactiveTime;
        }

        public String getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(String activeTime) {
            this.activeTime = activeTime;
        }

        public String getAvailablity() {
            return availablity;
        }

        public void setAvailablity(String availablity) {
            this.availablity = availablity;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

    }

}
