package com.example.isadminapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.isadminapp.R;
import com.example.isadminapp.activity.AddPaymentActivitySearch;
import com.example.isadminapp.activity.ChatSearchActivity;
import com.example.isadminapp.activity.ManageSession;
import com.example.isadminapp.activity.PaymentActivity;
import com.example.isadminapp.activity.ProfileViewActivity;
import com.example.isadminapp.activity.S;
import com.example.isadminapp.activity.WatchChatActivity;
import com.example.isadminapp.constant.Constants;
import com.example.isadminapp.preference.PreferenceManager;


public class Dashboard extends Fragment {

    PreferenceManager preferenceManager;
    TextView name , role ;
    Button advanceSearch, searchUser, checkChat, buttonSession;



    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        linearLayout = view.findViewById(R.id.ll_profile);
        name = view.findViewById(R.id.tv_name);
        role = view.findViewById(R.id.role);
        advanceSearch = view.findViewById(R.id.btn_advance_search);
        searchUser = view.findViewById(R.id.search_button_user);
        checkChat = view.findViewById(R.id.btn_check_chat);
        buttonSession = view.findViewById(R.id.btn_session);


        buttonSession.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ManageSession.class);
            startActivity(intent);
        });

        linearLayout.setOnClickListener(v->{
            Intent i = new Intent(v.getContext(), ProfileViewActivity.class);
            startActivity(i);
        });

        preferenceManager = new PreferenceManager(view.getContext());

        name.setText(preferenceManager.getString(Constants.NAME));
        role.setText(preferenceManager.getString(Constants.ROLE).replace("_"," "));

        advanceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });

        checkChat.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChatSearchActivity.class);
            startActivity(intent);
        });

        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), S.class);
                startActivity(intent);

            }
        });

        return view;
    }
}