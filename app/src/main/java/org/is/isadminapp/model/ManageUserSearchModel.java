package org.is.isadminapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManageUserSearchModel {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("schoolId")
    @Expose
    private Integer schoolId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list;
    @SerializedName("message")
    @Expose
    private String message;

    public ManageUserSearchModel(String email, String fullName, Integer schoolId) {
        this.email = email;
        this.fullName = fullName;
        this.schoolId = schoolId;
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("roleId")
        @Expose
        private Integer roleId;
        @SerializedName("lastUpdatedBy")
        @Expose
        private Integer lastUpdatedBy;
        @SerializedName("lastUpdatedDate")
        @Expose
        private Long lastUpdatedDate;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("userFullName")
        @Expose
        private String userFullName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("userLoginHash")
        @Expose
        private Object userLoginHash;
        @SerializedName("firstReset")
        @Expose
        private Integer firstReset;
        @SerializedName("uniquId")
        @Expose
        private String uniquId;
        @SerializedName("school")
        @Expose
        private Integer school;
        @SerializedName("standard")
        @Expose
        private Integer standard;
        @SerializedName("parentUserRole")
        @Expose
        private String parentUserRole;
        @SerializedName("countryTimezone")
        @Expose
        private String countryTimezone;
        @SerializedName("enabled")
        @Expose
        private Integer enabled;
        @SerializedName("deleted")
        @Expose
        private String deleted;
        @SerializedName("resetPasswordReq")
        @Expose
        private Object resetPasswordReq;
        @SerializedName("resetPasswordReqDate")
        @Expose
        private Object resetPasswordReqDate;
        @SerializedName("userLoginHashCreatedAt")
        @Expose
        private Long userLoginHashCreatedAt;
        @SerializedName("signupType")
        @Expose
        private String signupType;
        @SerializedName("studentStandardId")
        @Expose
        private Integer studentStandardId;
        @SerializedName("bitrixId")
        @Expose
        private Object bitrixId;
        @SerializedName("encryptedPassword")
        @Expose
        private String encryptedPassword;
        @SerializedName("fname")
        @Expose
        private String fname;
        @SerializedName("lname")
        @Expose
        private String lname;
        @SerializedName("mname")
        @Expose
        private String mname;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public Integer getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(Integer lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public Long getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(Long lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public Object getUserLoginHash() {
            return userLoginHash;
        }

        public void setUserLoginHash(Object userLoginHash) {
            this.userLoginHash = userLoginHash;
        }

        public Integer getFirstReset() {
            return firstReset;
        }

        public void setFirstReset(Integer firstReset) {
            this.firstReset = firstReset;
        }

        public String getUniquId() {
            return uniquId;
        }

        public void setUniquId(String uniquId) {
            this.uniquId = uniquId;
        }

        public Integer getSchool() {
            return school;
        }

        public void setSchool(Integer school) {
            this.school = school;
        }

        public Integer getStandard() {
            return standard;
        }

        public void setStandard(Integer standard) {
            this.standard = standard;
        }

        public String getParentUserRole() {
            return parentUserRole;
        }

        public void setParentUserRole(String parentUserRole) {
            this.parentUserRole = parentUserRole;
        }

        public String getCountryTimezone() {
            return countryTimezone;
        }

        public void setCountryTimezone(String countryTimezone) {
            this.countryTimezone = countryTimezone;
        }

        public Integer getEnabled() {
            return enabled;
        }

        public void setEnabled(Integer enabled) {
            this.enabled = enabled;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public Object getResetPasswordReq() {
            return resetPasswordReq;
        }

        public void setResetPasswordReq(Object resetPasswordReq) {
            this.resetPasswordReq = resetPasswordReq;
        }

        public Object getResetPasswordReqDate() {
            return resetPasswordReqDate;
        }

        public void setResetPasswordReqDate(Object resetPasswordReqDate) {
            this.resetPasswordReqDate = resetPasswordReqDate;
        }

        public Long getUserLoginHashCreatedAt() {
            return userLoginHashCreatedAt;
        }

        public void setUserLoginHashCreatedAt(Long userLoginHashCreatedAt) {
            this.userLoginHashCreatedAt = userLoginHashCreatedAt;
        }

        public String getSignupType() {
            return signupType;
        }

        public void setSignupType(String signupType) {
            this.signupType = signupType;
        }

        public Integer getStudentStandardId() {
            return studentStandardId;
        }

        public void setStudentStandardId(Integer studentStandardId) {
            this.studentStandardId = studentStandardId;
        }

        public Object getBitrixId() {
            return bitrixId;
        }

        public void setBitrixId(Object bitrixId) {
            this.bitrixId = bitrixId;
        }

        public String getEncryptedPassword() {
            return encryptedPassword;
        }

        public void setEncryptedPassword(String encryptedPassword) {
            this.encryptedPassword = encryptedPassword;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

    }
}

