package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Models.Users;
import com.example.whatsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapters extends RecyclerView.Adapter<UsersAdapters.ViewHolder>{
ArrayList<Users> list ;
Context context;

    public UsersAdapters(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UsersAdapters.ViewHolder holder, int position) {
        Users users= list.get(position);
        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.ic_user).into(holder.image);
        holder.userName.setText(users.getUserName());




    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView userName,lastMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.profile_image);
            userName =itemView.findViewById(R.id.userNameList);
            lastMessage =itemView.findViewById(R.id.lastMessage);

        }
    }

}



