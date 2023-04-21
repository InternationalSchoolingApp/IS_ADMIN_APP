package com.example.isadminapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.isadminapp.R;
import com.example.isadminapp.activity.ChatWithStudent;
import com.example.isadminapp.activity.ChatWithTeacher;


public class Chat extends Fragment {

    TextView student, teacher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        student = view.findViewById(R.id.chat_with_student);
        teacher = view.findViewById(R.id.chat_with_teacher);

        student.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChatWithStudent.class);
            startActivity(intent);
        });

        teacher.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChatWithTeacher.class);
            startActivity(intent);
        });

        return view;
    }
}