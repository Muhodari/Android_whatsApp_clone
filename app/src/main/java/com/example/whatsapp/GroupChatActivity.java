package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.whatsapp.databinding.ActivityGroupChatBinding;

public class GroupChatActivity extends AppCompatActivity {

    ActivityGroupChatBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }



}