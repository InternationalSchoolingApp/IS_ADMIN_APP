package org.is.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import org.is.isadminapp.adapter.SelectedCourseAdapter;
import org.is.isadminapp.databinding.ActivityAdminProfileViewBinding;
import org.is.isadminapp.model.AdminProfileViewModel;
import org.is.isadminapp.retrofit.ApiInterface;
import org.is.isadminapp.retrofit.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminProfileViewActivity extends AppCompatActivity {


    ActivityAdminProfileViewBinding binding;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityAdminProfileViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.profileBackButton.setOnClickListener(v->onBackPressed());


        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            userId = (extra.getString("userId"));
            Log.d("userId", "onCreate: " + userId);
        }

        AdminProfileViewModel adminProfileViewModel = new AdminProfileViewModel(userId);
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        Call<AdminProfileViewModel> call = apiInterface.adminProfileViewModelCall(adminProfileViewModel);
        call.enqueue(new Callback<AdminProfileViewModel>() {
            @Override
            public void onResponse(Call<AdminProfileViewModel> call, Response<AdminProfileViewModel> response) {
                if (response.isSuccessful()){

                    String url = response.body().getImage();
                    Glide.with(AdminProfileViewActivity.this).load(url).into(binding.profileImage);
                    binding.name.setText(""+response.body().getStudentName());
                    binding.email.setText(""+response.body().getStudentEmail());
                    binding.parentEmail.setText(""+response.body().getParentEmail());
                    binding.parentName.setText(""+response.body().getParentName());
                    binding.parentNumber.setText(""+response.body().getParentContactNumber());
                    binding.rollNumber.setText(""+response.body().getRollNumber());
                    binding.phoneNumber.setText(""+response.body().getPhoneNumber());
                    binding.addmissionTv.setText(""+response.body().getAdmissionDate());
                    binding.location.setText("Country : "+response.body().getCountry() + "\n" + "State : "+response.body().getState() + "\n"+ "City : "+response.body().getCity());
                    binding.gradeTv.setText(""+response.body().getStandard());
                    binding.regType.setText(""+response.body().getRegType());
                    binding.timeZone.setText(""+response.body().getTimezone());
                    binding.gradeTv.setText(""+response.body().getStandard());

                    List<AdminProfileViewModel.List> notification = response.body().getList();

                    if (notification!=null && !notification.isEmpty()){
                        SelectedCourseAdapter notificationAdapter = new SelectedCourseAdapter(notification);
                        binding.selectedCourse.setAdapter(notificationAdapter);
                    }


                }
            }

            @Override
            public void onFailure(Call<AdminProfileViewModel> call, Throwable t) {

            }
        });


    }
}