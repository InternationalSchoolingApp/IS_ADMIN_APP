package org.is.isadminapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminProfileViewModel {

    @SerializedName("standard")
    @Expose
    private String standard;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("image")
    @Expose
    private String image;

    public AdminProfileViewModel(String userId) {
        this.userId = userId;
    }

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("admissionDate")
    @Expose
    private String admissionDate;
    @SerializedName("parentEmail")
    @Expose
    private String parentEmail;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("parentContactNumber")
    @Expose
    private String parentContactNumber;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list;
    @SerializedName("studentEmail")
    @Expose
    private String studentEmail;
    @SerializedName("parentName")
    @Expose
    private String parentName;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("rollNumber")
    @Expose
    private String rollNumber;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("enrollmentType")
    @Expose
    private String enrollmentType;
    @SerializedName("regType")
    @Expose
    private String regType;

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParentContactNumber() {
        return parentContactNumber;
    }

    public void setParentContactNumber(String parentContactNumber) {
        this.parentContactNumber = parentContactNumber;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEnrollmentType() {
        return enrollmentType;
    }

    public void setEnrollmentType(String enrollmentType) {
        this.enrollmentType = enrollmentType;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public class List {

        @SerializedName("costNineMonth")
        @Expose
        private Integer costNineMonth;
        @SerializedName("courseType")
        @Expose
        private String courseType;
        @SerializedName("subjectIcon")
        @Expose
        private String subjectIcon;
        @SerializedName("standardId")
        @Expose
        private Integer standardId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("courseFilled")
        @Expose
        private Boolean courseFilled;
        @SerializedName("subjectId")
        @Expose
        private Integer subjectId;
        @SerializedName("duration")
        @Expose
        private Integer duration;
        @SerializedName("subjectHint")
        @Expose
        private Integer subjectHint;
        @SerializedName("entityName")
        @Expose
        private String entityName;
        @SerializedName("newParentId")
        @Expose
        private Integer newParentId;
        @SerializedName("schoolId")
        @Expose
        private Integer schoolId;
        @SerializedName("term")
        @Expose
        private String term;
        @SerializedName("courseId")
        @Expose
        private Integer courseId;
        @SerializedName("seq")
        @Expose
        private Integer seq;
        @SerializedName("subjectName")
        @Expose
        private String subjectName;
        @SerializedName("costThreeMonth")
        @Expose
        private Integer costThreeMonth;
        @SerializedName("isCompulsary")
        @Expose
        private Integer isCompulsary;
        @SerializedName("subjectTitle")
        @Expose
        private String subjectTitle;
        @SerializedName("isCompulsaryForBatch")
        @Expose
        private Integer isCompulsaryForBatch;
        @SerializedName("costSixMonth")
        @Expose
        private Integer costSixMonth;
        @SerializedName("active")
        @Expose
        private String active;
        @SerializedName("shownStatusMigration")
        @Expose
        private String shownStatusMigration;
        @SerializedName("shownStatus")
        @Expose
        private String shownStatus;
        @SerializedName("one2oneSsnAvailabaility")
        @Expose
        private Integer one2oneSsnAvailabaility;
        @SerializedName("parentId")
        @Expose
        private Integer parentId;
        @SerializedName("fullTimeCourseId")
        @Expose
        private String fullTimeCourseId;
        @SerializedName("courseProviderId")
        @Expose
        private Integer courseProviderId;
        @SerializedName("lastUpdatedDate")
        @Expose
        private String lastUpdatedDate;
        @SerializedName("deleted")
        @Expose
        private String deleted;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("costAnnual")
        @Expose
        private Integer costAnnual;
        @SerializedName("isBelongsToBatch")
        @Expose
        private Integer isBelongsToBatch;
        @SerializedName("subjectCredit")
        @Expose
        private Double subjectCredit;
        @SerializedName("days")
        @Expose
        private String days;
        @SerializedName("subjectCode")
        @Expose
        private String subjectCode;
        @SerializedName("status")
        @Expose
        private Integer status;

        public Integer getCostNineMonth() {
            return costNineMonth;
        }

        public void setCostNineMonth(Integer costNineMonth) {
            this.costNineMonth = costNineMonth;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public String getSubjectIcon() {
            return subjectIcon;
        }

        public void setSubjectIcon(String subjectIcon) {
            this.subjectIcon = subjectIcon;
        }

        public Integer getStandardId() {
            return standardId;
        }

        public void setStandardId(Integer standardId) {
            this.standardId = standardId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Boolean getCourseFilled() {
            return courseFilled;
        }

        public void setCourseFilled(Boolean courseFilled) {
            this.courseFilled = courseFilled;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Integer getSubjectHint() {
            return subjectHint;
        }

        public void setSubjectHint(Integer subjectHint) {
            this.subjectHint = subjectHint;
        }

        public String getEntityName() {
            return entityName;
        }

        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }

        public Integer getNewParentId() {
            return newParentId;
        }

        public void setNewParentId(Integer newParentId) {
            this.newParentId = newParentId;
        }

        public Integer getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(Integer schoolId) {
            this.schoolId = schoolId;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public Integer getSeq() {
            return seq;
        }

        public void setSeq(Integer seq) {
            this.seq = seq;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public Integer getCostThreeMonth() {
            return costThreeMonth;
        }

        public void setCostThreeMonth(Integer costThreeMonth) {
            this.costThreeMonth = costThreeMonth;
        }

        public Integer getIsCompulsary() {
            return isCompulsary;
        }

        public void setIsCompulsary(Integer isCompulsary) {
            this.isCompulsary = isCompulsary;
        }

        public String getSubjectTitle() {
            return subjectTitle;
        }

        public void setSubjectTitle(String subjectTitle) {
            this.subjectTitle = subjectTitle;
        }

        public Integer getIsCompulsaryForBatch() {
            return isCompulsaryForBatch;
        }

        public void setIsCompulsaryForBatch(Integer isCompulsaryForBatch) {
            this.isCompulsaryForBatch = isCompulsaryForBatch;
        }

        public Integer getCostSixMonth() {
            return costSixMonth;
        }

        public void setCostSixMonth(Integer costSixMonth) {
            this.costSixMonth = costSixMonth;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getShownStatusMigration() {
            return shownStatusMigration;
        }

        public void setShownStatusMigration(String shownStatusMigration) {
            this.shownStatusMigration = shownStatusMigration;
        }

        public String getShownStatus() {
            return shownStatus;
        }

        public void setShownStatus(String shownStatus) {
            this.shownStatus = shownStatus;
        }

        public Integer getOne2oneSsnAvailabaility() {
            return one2oneSsnAvailabaility;
        }

        public void setOne2oneSsnAvailabaility(Integer one2oneSsnAvailabaility) {
            this.one2oneSsnAvailabaility = one2oneSsnAvailabaility;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getFullTimeCourseId() {
            return fullTimeCourseId;
        }

        public void setFullTimeCourseId(String fullTimeCourseId) {
            this.fullTimeCourseId = fullTimeCourseId;
        }

        public Integer getCourseProviderId() {
            return courseProviderId;
        }

        public void setCourseProviderId(Integer courseProviderId) {
            this.courseProviderId = courseProviderId;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Integer getCostAnnual() {
            return costAnnual;
        }

        public void setCostAnnual(Integer costAnnual) {
            this.costAnnual = costAnnual;
        }

        public Integer getIsBelongsToBatch() {
            return isBelongsToBatch;
        }

        public void setIsBelongsToBatch(Integer isBelongsToBatch) {
            this.isBelongsToBatch = isBelongsToBatch;
        }

        public Double getSubjectCredit() {
            return subjectCredit;
        }

        public void setSubjectCredit(Double subjectCredit) {
            this.subjectCredit = subjectCredit;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getSubjectCode() {
            return subjectCode;
        }

        public void setSubjectCode(String subjectCode) {
            this.subjectCode = subjectCode;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

    }

}
