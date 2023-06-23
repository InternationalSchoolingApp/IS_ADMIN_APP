package org.is.isadminapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.is.isadminapp.R;
import org.is.isadminapp.activity.SplashPage;
import org.is.isadminapp.common.LogoutDone;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.preference.PreferenceManager;

public class More extends Fragment {

    androidx.appcompat.widget.AppCompatButton logoutbtn;


    PreferenceManager preferenceManager ;


    String firebaseToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        logoutbtn = view.findViewById(R.id.log_out_btn_more);
        preferenceManager = new PreferenceManager(getContext());

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int userId = preferenceManager.getInt(Constants.USER_ID);
                System.out.println(userId);
                Log.d("TAG", "onClick: "+userId);
                String usermail = preferenceManager.getString(Constants.USER_EMAIL);
                firebaseToken = preferenceManager.getString(Constants.FIREBASE_TOKEN);
                int platformId = preferenceManager.getInt(Constants.PLATFORM_ID);
                LogoutDone logoutDone = new LogoutDone();
                if(logoutDone.logout(platformId,userId,usermail)){
                    preferenceManager.clear();
                    preferenceManager.putString(Constants.FIREBASE_TOKEN, firebaseToken);
                    Intent intent = new Intent(getActivity(), SplashPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    getActivity().finishAffinity();
                }else {
                    Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return view;
    }
}