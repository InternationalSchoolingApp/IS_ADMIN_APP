package org.is.isadminapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudentAvailablityForChatModel {

    @SerializedName("studentList")
    @Expose
    private List<Student> studentList;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("userName")
    @Expose
    private String userName;

    public StudentAvailablityForChatModel(String userName) {
        this.userName = userName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Student {

        @SerializedName("grade")
        @Expose
        private String grade;
        @SerializedName("emailId")
        @Expose
        private String emailId;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userName")
        @Expose
        private String userName;

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

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
