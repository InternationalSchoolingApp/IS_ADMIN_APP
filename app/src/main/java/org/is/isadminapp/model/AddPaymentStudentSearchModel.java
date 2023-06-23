package org.is.isadminapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPaymentStudentSearchModel {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list;
    @SerializedName("message")
    @Expose
    private String message;

    public AddPaymentStudentSearchModel(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class List {

        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("standard")
        @Expose
        private String standard;
        @SerializedName("standardId")
        @Expose
        private String standardId;
        @SerializedName("studentId")
        @Expose
        private String studentId;
        @SerializedName("studentStandardId")
        @Expose
        private String studentStandardId;
        @SerializedName("lockedPaymentRule")
        @Expose
        private String lockedPaymentRule;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public String getStandardId() {
            return standardId;
        }

        public void setStandardId(String standardId) {
            this.standardId = standardId;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentStandardId() {
            return studentStandardId;
        }

        public void setStudentStandardId(String studentStandardId) {
            this.studentStandardId = studentStandardId;
        }

        public String getLockedPaymentRule() {
            return lockedPaymentRule;
        }

        public void setLockedPaymentRule(String lockedPaymentRule) {
            this.lockedPaymentRule = lockedPaymentRule;
        }

    }

}
