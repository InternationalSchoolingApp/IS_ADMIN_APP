package com.example.isadminapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentSearchModel {
    public PaymentSearchModel(String moduleId, List<Object> paymentType, List<Object> paymentVia, String registrationType, List<Object> gradeId, String enrollStatus, String studentName, String studentEmail, List<Object> countryId, List<Object> paymentMode, String transactionRefNumber, String userRefNumber, List<Object> paymentStatus, String paymentDateFrom, String paymentDateTo, String sortBy, String orderBy, String startPosition, String numberOfRecords, String schoolId, String schoolUUID, String userId) {
        this.moduleId = moduleId;
        this.paymentType = paymentType;
        this.paymentVia = paymentVia;
        this.registrationType = registrationType;
        this.gradeId = gradeId;
        this.enrollStatus = enrollStatus;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.countryId = countryId;
        this.paymentMode = paymentMode;
        this.transactionRefNumber = transactionRefNumber;
        this.userRefNumber = userRefNumber;
        this.paymentStatus = paymentStatus;
        this.paymentDateFrom = paymentDateFrom;
        this.paymentDateTo = paymentDateTo;
        this.sortBy = sortBy;
        this.orderBy = orderBy;
        this.startPosition = startPosition;
        this.numberOfRecords = numberOfRecords;
        this.schoolId = schoolId;
        this.schoolUUID = schoolUUID;
        this.userId = userId;
    }

    @SerializedName("moduleId")
    @Expose
    private String moduleId;
    @SerializedName("paymentType")
    @Expose
    private List<Object> paymentType;
    @SerializedName("paymentVia")
    @Expose
    private List<Object> paymentVia;
    @SerializedName("registrationType")
    @Expose
    private String registrationType;
    @SerializedName("gradeId")
    @Expose
    private List<Object> gradeId;
    @SerializedName("enrollStatus")
    @Expose
    private String enrollStatus;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("studentEmail")
    @Expose
    private String studentEmail;
    @SerializedName("countryId")
    @Expose
    private List<Object> countryId;
    @SerializedName("paymentMode")
    @Expose
    private List<Object> paymentMode;
    @SerializedName("transactionRefNumber")
    @Expose
    private String transactionRefNumber;
    @SerializedName("userRefNumber")
    @Expose
    private String userRefNumber;
    @SerializedName("paymentStatus")
    @Expose
    private List<Object> paymentStatus;
    @SerializedName("paymentDateFrom")
    @Expose
    private String paymentDateFrom;
    @SerializedName("paymentDateTo")
    @Expose
    private String paymentDateTo;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;
    @SerializedName("orderBy")
    @Expose
    private String orderBy;
    @SerializedName("startPosition")
    @Expose
    private String startPosition;
    @SerializedName("numberOfRecords")
    @Expose
    private String numberOfRecords;
    @SerializedName("schoolId")
    @Expose
    private String schoolId;
    @SerializedName("schoolUUID")
    @Expose
    private String schoolUUID;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userType")
    @Expose
    private Object userType;
    @SerializedName("statusCode")
    @Expose
    private Object statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("advancePaymentSearchResponseDTO")
    @Expose
    private List<AdvancePaymentSearchResponseDTO> advancePaymentSearchResponseDTO;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public List<Object> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<Object> paymentType) {
        this.paymentType = paymentType;
    }

    public List<Object> getPaymentVia() {
        return paymentVia;
    }

    public void setPaymentVia(List<Object> paymentVia) {
        this.paymentVia = paymentVia;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public List<Object> getGradeId() {
        return gradeId;
    }

    public void setGradeId(List<Object> gradeId) {
        this.gradeId = gradeId;
    }

    public String getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(String enrollStatus) {
        this.enrollStatus = enrollStatus;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<Object> getCountryId() {
        return countryId;
    }

    public void setCountryId(List<Object> countryId) {
        this.countryId = countryId;
    }

    public List<Object> getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(List<Object> paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionRefNumber() {
        return transactionRefNumber;
    }

    public void setTransactionRefNumber(String transactionRefNumber) {
        this.transactionRefNumber = transactionRefNumber;
    }

    public String getUserRefNumber() {
        return userRefNumber;
    }

    public void setUserRefNumber(String userRefNumber) {
        this.userRefNumber = userRefNumber;
    }

    public List<Object> getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(List<Object> paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDateFrom() {
        return paymentDateFrom;
    }

    public void setPaymentDateFrom(String paymentDateFrom) {
        this.paymentDateFrom = paymentDateFrom;
    }

    public String getPaymentDateTo() {
        return paymentDateTo;
    }

    public void setPaymentDateTo(String paymentDateTo) {
        this.paymentDateTo = paymentDateTo;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(String numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolUUID() {
        return schoolUUID;
    }

    public void setSchoolUUID(String schoolUUID) {
        this.schoolUUID = schoolUUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getUserType() {
        return userType;
    }

    public void setUserType(Object userType) {
        this.userType = userType;
    }

    public Object getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Object statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AdvancePaymentSearchResponseDTO> getAdvancePaymentSearchResponseDTO() {
        return advancePaymentSearchResponseDTO;
    }

    public void setAdvancePaymentSearchResponseDTO(List<AdvancePaymentSearchResponseDTO> advancePaymentSearchResponseDTO) {
        this.advancePaymentSearchResponseDTO = advancePaymentSearchResponseDTO;
    }


    public class AdvancePaymentSearchResponseDTO {

        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("userPaymentDetailsId")
        @Expose
        private Integer userPaymentDetailsId;
        @SerializedName("standardId")
        @Expose
        private Integer standardId;
        @SerializedName("studentStandardId")
        @Expose
        private Integer studentStandardId;
        @SerializedName("serialNum")
        @Expose
        private Integer serialNum;
        @SerializedName("transactionRefNumber")
        @Expose
        private String transactionRefNumber;
        @SerializedName("userRefNumber")
        @Expose
        private String userRefNumber;
        @SerializedName("studentRollNumber")
        @Expose
        private String studentRollNumber;
        @SerializedName("studentName")
        @Expose
        private String studentName;
        @SerializedName("studentEmail")
        @Expose
        private String studentEmail;
        @SerializedName("gradeName")
        @Expose
        private String gradeName;
        @SerializedName("paymentTitle")
        @Expose
        private String paymentTitle;
        @SerializedName("scheduledPayDate")
        @Expose
        private String scheduledPayDate;
        @SerializedName("payDate")
        @Expose
        private String payDate;
        @SerializedName("payAmount")
        @Expose
        private String payAmount;
        @SerializedName("paymentTransferType")
        @Expose
        private String paymentTransferType;
        @SerializedName("pgName")
        @Expose
        private String pgName;
        @SerializedName("paymentStatus")
        @Expose
        private String paymentStatus;
        @SerializedName("remarks")
        @Expose
        private String remarks;
        @SerializedName("countryName")
        @Expose
        private String countryName;
        @SerializedName("additionalPayment")
        @Expose
        private String additionalPayment;
        @SerializedName("reviewPayment")
        @Expose
        private Object reviewPayment;
        @SerializedName("proofOfPayment")
        @Expose
        private String proofOfPayment;
        @SerializedName("recieptLink")
        @Expose
        private String recieptLink;
        @SerializedName("wurecieptLink")
        @Expose
        private Object wurecieptLink;
        @SerializedName("selectedCurrency")
        @Expose
        private Object selectedCurrency;
        @SerializedName("payCurrency")
        @Expose
        private Object payCurrency;
        @SerializedName("conversionRation")
        @Expose
        private Object conversionRation;
        @SerializedName("paymentName")
        @Expose
        private String paymentName;
        @SerializedName("planName")
        @Expose
        private String planName;
        @SerializedName("shareLink")
        @Expose
        private String shareLink;
        @SerializedName("registrationType")
        @Expose
        private String registrationType;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getUserPaymentDetailsId() {
            return userPaymentDetailsId;
        }

        public void setUserPaymentDetailsId(Integer userPaymentDetailsId) {
            this.userPaymentDetailsId = userPaymentDetailsId;
        }

        public Integer getStandardId() {
            return standardId;
        }

        public void setStandardId(Integer standardId) {
            this.standardId = standardId;
        }

        public Integer getStudentStandardId() {
            return studentStandardId;
        }

        public void setStudentStandardId(Integer studentStandardId) {
            this.studentStandardId = studentStandardId;
        }

        public Integer getSerialNum() {
            return serialNum;
        }

        public void setSerialNum(Integer serialNum) {
            this.serialNum = serialNum;
        }

        public String getTransactionRefNumber() {
            return transactionRefNumber;
        }

        public void setTransactionRefNumber(String transactionRefNumber) {
            this.transactionRefNumber = transactionRefNumber;
        }

        public String getUserRefNumber() {
            return userRefNumber;
        }

        public void setUserRefNumber(String userRefNumber) {
            this.userRefNumber = userRefNumber;
        }

        public String getStudentRollNumber() {
            return studentRollNumber;
        }

        public void setStudentRollNumber(String studentRollNumber) {
            this.studentRollNumber = studentRollNumber;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getStudentEmail() {
            return studentEmail;
        }

        public void setStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getPaymentTitle() {
            return paymentTitle;
        }

        public void setPaymentTitle(String paymentTitle) {
            this.paymentTitle = paymentTitle;
        }

        public String getScheduledPayDate() {
            return scheduledPayDate;
        }

        public void setScheduledPayDate(String scheduledPayDate) {
            this.scheduledPayDate = scheduledPayDate;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getPaymentTransferType() {
            return paymentTransferType;
        }

        public void setPaymentTransferType(String paymentTransferType) {
            this.paymentTransferType = paymentTransferType;
        }

        public String getPgName() {
            return pgName;
        }

        public void setPgName(String pgName) {
            this.pgName = pgName;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getAdditionalPayment() {
            return additionalPayment;
        }

        public void setAdditionalPayment(String additionalPayment) {
            this.additionalPayment = additionalPayment;
        }

        public Object getReviewPayment() {
            return reviewPayment;
        }

        public void setReviewPayment(Object reviewPayment) {
            this.reviewPayment = reviewPayment;
        }

        public String getProofOfPayment() {
            return proofOfPayment;
        }

        public void setProofOfPayment(String proofOfPayment) {
            this.proofOfPayment = proofOfPayment;
        }

        public String getRecieptLink() {
            return recieptLink;
        }

        public void setRecieptLink(String recieptLink) {
            this.recieptLink = recieptLink;
        }

        public Object getWurecieptLink() {
            return wurecieptLink;
        }

        public void setWurecieptLink(Object wurecieptLink) {
            this.wurecieptLink = wurecieptLink;
        }

        public Object getSelectedCurrency() {
            return selectedCurrency;
        }

        public void setSelectedCurrency(Object selectedCurrency) {
            this.selectedCurrency = selectedCurrency;
        }

        public Object getPayCurrency() {
            return payCurrency;
        }

        public void setPayCurrency(Object payCurrency) {
            this.payCurrency = payCurrency;
        }

        public Object getConversionRation() {
            return conversionRation;
        }

        public void setConversionRation(Object conversionRation) {
            this.conversionRation = conversionRation;
        }

        public String getPaymentName() {
            return paymentName;
        }

        public void setPaymentName(String paymentName) {
            this.paymentName = paymentName;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getShareLink() {
            return shareLink;
        }

        public void setShareLink(String shareLink) {
            this.shareLink = shareLink;
        }

        public String getRegistrationType() {
            return registrationType;
        }

        public void setRegistrationType(String registrationType) {
            this.registrationType = registrationType;
        }

    }

}
