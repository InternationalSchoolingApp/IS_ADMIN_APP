package com.example.isadminapp.retrofit;

import com.example.isadminapp.model.ActivateChatModel;
import com.example.isadminapp.model.AddPaymentStudentSearchModel;
import com.example.isadminapp.model.AdminProfileModel;
import com.example.isadminapp.model.AdminProfileViewModel;
import com.example.isadminapp.model.ChatStatusCheck;
import com.example.isadminapp.model.ChatWithTeacherModel;
import com.example.isadminapp.model.DashboardNotificationModel;
import com.example.isadminapp.model.FirebaseTokenModel;
import com.example.isadminapp.model.ForgetPasswordModel;
import com.example.isadminapp.model.LoginModel;
import com.example.isadminapp.model.ManageSessionModel;
import com.example.isadminapp.model.ManageUserSearchModel;
import com.example.isadminapp.model.NotificationForApp;
import com.example.isadminapp.model.PaymentSearchModel;
import com.example.isadminapp.model.StudentAvailablityForChatModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {


    @POST("forgot-password")
    Call<ForgetPasswordModel> forgetPassword(@Body ForgetPasswordModel forgetPasswordModel);

    @POST("login-platform-admin")
    Call<LoginModel> loginPostData(@Body LoginModel loginModel);
//
    @POST("device-token")
    Call<FirebaseTokenModel> firebaseToken(@Body FirebaseTokenModel firebaseTokenModel);



    @POST("notification-check")
    Call<DashboardNotificationModel> dashboardNotificationModel(@Body DashboardNotificationModel dashboardNotificationModel);
//
//    @POST("create-my-notes")
//    Call<CreateMyNotes> createMyNotes(@Body CreateMyNotes createMyNotes);
//
//    @POST("get-my-notes")
//    Call<GetNotes> getMyNotes(@Body GetNotes getNotes);
//
//
//    @POST("teacher-image")
//    Call<TeacherInfoModel> teacherInfoModel(@Body TeacherInfoModel teacherInfoModel);
//
//    @POST("get-student-mapped-teacher")
//    Call<AssignTeacherModel> assignTeacherChat(@Body AssignTeacherModel assignTeacherModel);
//
//    @POST("school-calendar")
//    Call<ScheduleModel> getSchedule(@Body ScheduleModel scheduleModel);
//
//    @POST("get-student-profile-view-app")
//    Call<UserProfile> profilePostData(@Body UserProfile userProfile);
//
//    @POST("logout-platform-App")
//    Call<LogoutModel> logoutPostData(@Body LogoutModel logoutModel);
//
//
    @POST("admin-profile")
    Call<AdminProfileModel> admin(@Body AdminProfileModel dashBoardModel);
//
    @POST("get-notification-app")
    Call<NotificationForApp> notificationForApp(@Body NotificationForApp notificationForApp);


    @POST("advance-payment-search-content")
    Call<PaymentSearchModel> paymentSearch(@Body PaymentSearchModel paymentSearchModel);

    @POST("student-search")
    Call<AddPaymentStudentSearchModel> searchStudent(@Body AddPaymentStudentSearchModel subjectListPerformance);

    @POST("user-search")
    Call<ManageUserSearchModel> searchManageStudent(@Body ManageUserSearchModel manageUserSearchModel);

    @POST("manage-session")
    Call<ManageSessionModel> searchManageSession(@Body ManageSessionModel manageSessionModel);

    @POST("app-profile-admin-view")
    Call<AdminProfileViewModel> adminProfileViewModelCall(@Body AdminProfileViewModel manageSessionModel);


    @POST("app-approved-teacher")
    Call<ChatWithTeacherModel> chatTeacherSearch(@Body ChatWithTeacherModel manageSessionModel);

    @POST("chat-status-check-per-user")
    Call<ChatStatusCheck> chatStatusCheck(@Body ChatStatusCheck chatStatusCheck);

    @POST("de-activate-admin-for-chat")
    Call<ActivateChatModel> deactivate(@Body ActivateChatModel activateChatModel);

    @POST("activate-admin-for-chat")
    Call<ActivateChatModel> activate(@Body ActivateChatModel deactivateChatModel);

    @POST("student-available-for-chat")
    Call<StudentAvailablityForChatModel> getStudentsForChat(@Body StudentAvailablityForChatModel studentAvailablityForChatModel);


}
