package org.is.isadminapp.common;


import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.model.LogoutModel;
import org.is.isadminapp.retrofit.ApiInterface;
import org.is.isadminapp.retrofit.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutDone {


    public Boolean logout(Integer platformId, int userId, String usermail) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference notificationsCollection = firestore.collection(Constants.FIREBASE_USER_DB);

        Query query = notificationsCollection.whereEqualTo("userId", userId);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String documentId = document.getId();
                    notificationsCollection.document(documentId)
                            .update("firebaseToken", "")
                            .addOnSuccessListener(aVoid -> {
                                Log.d("UPDATED", "logout: ");
                            })
                            .addOnFailureListener(e -> {
                            });
                }
            } else {
            }
        });

        MobileModel mobileModel = new MobileModel();
        String model = mobileModel.getDeviceName();
        final Integer[] i = {0};
        ApiInterface apiInterface = RetroFitClient.getRetrofit().create(ApiInterface.class);
        final LogoutModel logoutModel = new LogoutModel(platformId, userId, model, 1, usermail);
        Call<LogoutModel> call = apiInterface.logoutPostData(logoutModel);
        call.enqueue(new Callback<LogoutModel>() {
            @Override
            public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                if (response.body().getStatus().equals("success")) {
                    i[0] = 0;
                } else {
                    i[0] = 1;
                }
            }

            @Override
            public void onFailure(Call<LogoutModel> call, Throwable t) {
                i[0] = 2;
            }

        });
        if(i[0]==0){
            return  true;
        }
        else {
            return  false;
        }

    }
}
