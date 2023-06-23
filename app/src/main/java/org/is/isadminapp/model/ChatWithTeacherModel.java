package org.is.isadminapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatWithTeacherModel {

    @SerializedName("approvedTeacher")
    @Expose
    private List<ApprovedTeacher> approvedTeacher;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("userName")
    @Expose
    private String userName;

    public ChatWithTeacherModel(String userName) {
        this.userName = userName;
    }

    public List<ApprovedTeacher> getApprovedTeacher() {
        return approvedTeacher;
    }

    public void setApprovedTeacher(List<ApprovedTeacher> approvedTeacher) {
        this.approvedTeacher = approvedTeacher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class ApprovedTeacher {

        @SerializedName("emailId")
        @Expose
        private String emailId;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userName")
        @Expose
        private String userName;

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

    }

}
