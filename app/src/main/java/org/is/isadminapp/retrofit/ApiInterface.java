package org.is.isadminapp.retrofit;

import org.is.isadminapp.model.ActivateChatModel;
import org.is.isadminapp.model.AddPaymentStudentSearchModel;
import org.is.isadminapp.model.AdminChatActivationLog;
import org.is.isadminapp.model.AdminProfileModel;
import org.is.isadminapp.model.AdminProfileViewModel;
import org.is.isadminapp.model.ChatStatusCheck;
import org.is.isadminapp.model.ChatWithTeacherModel;
import org.is.isadminapp.model.DashboardNotificationModel;
import org.is.isadminapp.model.FirebaseTokenModel;
import org.is.isadminapp.model.ForgetPasswordModel;
import org.is.isadminapp.model.LoginModel;
import org.is.isadminapp.model.LogoutModel;
import org.is.isadminapp.model.ManageSessionModel;
import org.is.isadminapp.model.ManageUserSearchModel;
import org.is.isadminapp.model.NotificationForApp;
import org.is.isadminapp.model.PaymentSearchModel;
import org.is.isadminapp.model.StudentAvailablityForChatModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("logout-platform-App")
    Call<LogoutModel> logoutPostData(@Body LogoutModel logoutModel);


    @POST("forgot-password")
    Call<ForgetPasswordModel> forgetPassword(@Body ForgetPasswordModel forgetPasswordModel);

    @POST("login-platform-admin")
    Call<LoginModel> loginPostData(@Body LoginModel loginModel);

    @GET("get-current-time-zone-user")
    Call<String> getCurrentTimeZone(@Query("userId") int userId);

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

    @POST("admin-chat-activation-log")
    Call<AdminChatActivationLog> getAdminChatLog(@Body AdminChatActivationLog adminChatActivationLog);


}
