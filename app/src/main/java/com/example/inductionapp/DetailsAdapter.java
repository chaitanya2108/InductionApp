package com.example.inductionapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;

public class  DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {

    ArrayList<DetailsModel> aList;
    Context context;
    String blockA = "https://goo.gl/maps/CM4e3VGPrwuQQMXm8";
    String blockB = "https://goo.gl/maps/xizvwfQk6zejnNmF8";
    String blockC = "https://goo.gl/maps/H75ZeknW2eeTcXRf8";

    public DetailsAdapter(Context context, ArrayList<DetailsModel> aList) {
        this.aList = aList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.detailslist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DetailsModel model = aList.get(position);
        holder.name.setText(model.getName());
        holder.facultyName.setText(model.getFacultyName());
        Glide.with(context).load(aList.get(position).getImage()).into(holder.image);
        holder.description.setText(model.getDescription());
        holder.block.setText(model.getBlock());
        holder.room.setText(model.getRoom());

        String blockName = aList.get(position).getBlock();

        holder.block.setOnClickListener(new View.OnClickListener(){

            Uri uri;

            @Override
            public void onClick(View v) {
                switch(blockName) {
                    case "Block: A":
                        uri = Uri.parse(blockA);
                        v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri ));
                        break;

                    case "Block: B":
                        uri = Uri.parse(blockB);
                        v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri ));
                        break;

                    case "Block: C":
                        uri = Uri.parse(blockC);
                        v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri ));
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return aList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, facultyName, description, block, room;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            facultyName = itemView.findViewById(R.id.facultyname);
            image = itemView.findViewById(R.id.imageView2);
            description = itemView.findViewById(R.id.description);
            block = itemView.findViewById(R.id.block);
            room = itemView.findViewById(R.id.room);
        }
    }

}
