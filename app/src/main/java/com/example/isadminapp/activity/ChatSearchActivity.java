package com.example.isadminapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.isadminapp.databinding.ActivityChatSearchBinding;

public class ChatSearchActivity extends AppCompatActivity {

    ActivityChatSearchBinding binding;
    String student, teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();

    }

    private void setListener() {

        binding.btnAdvanceSearch.setOnClickListener(v->{
            if (!binding.nameSearch.getText().toString().toLowerCase().trim().equals("") && !binding.emailSearch.getText().toString().toLowerCase().trim().equals("")) {
                student = binding.nameSearch.getText().toString().toLowerCase().trim();
                teacher = binding.emailSearch.getText().toString().toLowerCase().trim();
                go(student, teacher);
            }else{
                Toast.makeText(this, "Field Empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void go(String student, String teacher) {

        Intent intent = new Intent(this, WatchChatActivity.class);
        intent.putExtra("studentEmail", student);
        intent.putExtra("teacherEmail", teacher);
        this.startActivity(intent);

    }
}