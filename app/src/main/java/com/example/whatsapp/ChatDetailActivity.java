package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.Adapters.ChatAdapter;
import com.example.whatsapp.Models.MessageModel;
import com.example.whatsapp.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //        receive other users data or message

        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

       final String senderId= auth.getUid();
        String receiveId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");

        binding.userName.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.ic_user).into(binding.profileImage);

        // set back arrow
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ChatDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


 final ArrayList<MessageModel> messageModels= new ArrayList<>();

 final ChatAdapter chatAdapter = new ChatAdapter(messageModels,this);
 binding.chatRecyclerView.setAdapter(chatAdapter);

 LinearLayoutManager layoutManager= new LinearLayoutManager(this);
 binding.chatRecyclerView.setLayoutManager(layoutManager);



        final String senderRoom = senderId + receiveId;
        final String receiveRoom = receiveId + senderId;

//  send message
   binding.send.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           String message = binding.etMessage.getText().toString();
           final MessageModel model = new MessageModel(senderId,message);
           model.setTimestamp(new Date().getTime());
           binding.etMessage.setText("");

           database.getReference().child("chats")
                   .child(senderRoom)
                   .push()
                   .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {

               @Override
               public void onSuccess(Void aVoid) {
              database.getReference().child("chats")
                      .child(receiveRoom)
                      .push()
                      .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {


                  }
              });

               }
           });


       }
   });










    }
}