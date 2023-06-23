package org.is.isadminapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.is.isadminapp.R;
import org.is.isadminapp.activity.AddPaymentActivitySearch;
import org.is.isadminapp.activity.ChatActivateActivity;
import org.is.isadminapp.activity.ChatSearchActivity;
import org.is.isadminapp.activity.ManageSession;
import org.is.isadminapp.activity.PaymentActivity;
import org.is.isadminapp.activity.ProfileViewActivity;
import org.is.isadminapp.activity.S;
import org.is.isadminapp.activity.WatchChatActivity;
import org.is.isadminapp.constant.Constants;
import org.is.isadminapp.preference.PreferenceManager;


public class Dashboard extends Fragment {

    PreferenceManager preferenceManager;
    TextView name , role ;
    Button advanceSearch, searchUser, checkChat, buttonSession, chatCheckStatus;



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
        chatCheckStatus = view.findViewById(R.id.chat_activity_button);


        chatCheckStatus.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChatActivateActivity.class);
            startActivity(intent);
        });


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