package com.example.gridapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    Context context;
    ArrayList<ImageModel> data;

    public ImageAdapter(Context context, ArrayList<ImageModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_image,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       Glide.with(context)
                .asBitmap()
                .load("https://pokeres.bastionbot.org/images/pokemon/"+(position+1)+".png")
                .into(holder.image);


        holder.name.setText(data.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.image=itemView.findViewById(R.id.image);
            this.name=itemView.findViewById(R.id.name);

        }
    }
}
