package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Models.MessageModel;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;



    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType ==SENDER_VIEW_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewVolder(view);
        }
        else {
            View view= LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
            return new RecieverViewHolder(view);
        }
    }


    @Override
    public int getItemViewType(int position) {
       if(messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
     return  SENDER_VIEW_TYPE;
     }
       else {
           return RECEIVER_VIEW_TYPE;
       }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     MessageModel messageModel = messageModels.get(position);
    if(holder.getClass() == SenderViewVolder.class){
      ((SenderViewVolder)holder).senderMsg.setText(messageModel.getMessage());
   }
    else {
        ((RecieverViewHolder)holder).receiverMsg.setText(messageModel.getMessage());
    }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }



    //    receiver holder
    public class RecieverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMsg,receiverTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.receiverText);
            receiverTime = itemView.findViewById(R.id.receiverTime);

        }
    }

// sender Holder

    public class SenderViewVolder extends RecyclerView.ViewHolder{

          TextView senderMsg,senderTime;
        public SenderViewVolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);


        }
    }






}
