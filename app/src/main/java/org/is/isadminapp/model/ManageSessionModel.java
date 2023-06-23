package org.is.isadminapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManageSessionModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("studentEmail")
    @Expose
    private String studentEmail;

    public ManageSessionModel(String studentEmail, String studentName, Integer userId) {
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.userId = userId;
    }

    @SerializedName("studentName")
    @Expose
    private String studentName;

    @SerializedName("userId")
    @Expose
    private Integer userId;

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
        private Integer userId;
        @SerializedName("studentId")
        @Expose
        private Integer studentId;
        @SerializedName("studentName")
        @Expose
        private String studentName;
        @SerializedName("rollNo")
        @Expose
        private String rollNo;
        @SerializedName("gradeName")
        @Expose
        private String gradeName;
        @SerializedName("emailId")
        @Expose
        private String emailId;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("standardId")
        @Expose
        private Integer standardId;
        @SerializedName("courseProviderId")
        @Expose
        private Integer courseProviderId;
        @SerializedName("courseProviderName")
        @Expose
        private String courseProviderName;
        @SerializedName("enrollStatus")
        @Expose
        private String enrollStatus;
        @SerializedName("defaultEnrollmentStatus")
        @Expose
        private String defaultEnrollmentStatus;
        @SerializedName("sno")
        @Expose
        private Integer sno;
        @SerializedName("action")
        @Expose
        private String action;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getRollNo() {
            return rollNo;
        }

        public void setRollNo(String rollNo) {
            this.rollNo = rollNo;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getStandardId() {
            return standardId;
        }

        public void setStandardId(Integer standardId) {
            this.standardId = standardId;
        }

        public Integer getCourseProviderId() {
            return courseProviderId;
        }

        public void setCourseProviderId(Integer courseProviderId) {
            this.courseProviderId = courseProviderId;
        }

        public String getCourseProviderName() {
            return courseProviderName;
        }

        public void setCourseProviderName(String courseProviderName) {
            this.courseProviderName = courseProviderName;
        }

        public String getEnrollStatus() {
            return enrollStatus;
        }

        public void setEnrollStatus(String enrollStatus) {
            this.enrollStatus = enrollStatus;
        }

        public String getDefaultEnrollmentStatus() {
            return defaultEnrollmentStatus;
        }

        public void setDefaultEnrollmentStatus(String defaultEnrollmentStatus) {
            this.defaultEnrollmentStatus = defaultEnrollmentStatus;
        }

        public Integer getSno() {
            return sno;
        }

        public void setSno(Integer sno) {
            this.sno = sno;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

    }

}
