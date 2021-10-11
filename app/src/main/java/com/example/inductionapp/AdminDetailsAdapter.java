package com.example.inductionapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class AdminDetailsAdapter extends RecyclerView.Adapter<AdminDetailsAdapter.MyViewHolder>{

    private FirebaseDatabase fbase = FirebaseDatabase.getInstance();
    ArrayList<DetailsModel> aList;
    Context context;
    String blockA = "https://goo.gl/maps/CM4e3VGPrwuQQMXm8";
    String blockB = "https://goo.gl/maps/xizvwfQk6zejnNmF8";
    String blockC = "https://goo.gl/maps/H75ZeknW2eeTcXRf8";

    public AdminDetailsAdapter(Context context, ArrayList<DetailsModel> aList) {
        this.aList = aList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.admindetailslist, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admindetailslist,parent,false);
        return new AdminDetailsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDetailsAdapter.MyViewHolder holder, int position) {

        String text = aList.get(position).getName();

        ((MyViewHolder)holder).deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(text);
            }
        });

        DetailsModel model = aList.get(position);
        holder.name.setText(model.getName());
        holder.facultyName.setText(model.getFacultyName());
        Glide.with(context).load(aList.get(position).getImage()).into(holder.image);
        holder.description.setText(model.getDescription());
        holder.block.setText(model.getBlock());

        String blockName = aList.get(position).getBlock();

        holder.block.setOnClickListener(new View.OnClickListener() {

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
//                Log.i("TAG", "onClick: "+ aList.get(currentPosition).getBlock());
//                Log.i("TAG", "onClick: "+ (aList.get(currentPosition).getBlock() == "Block: A"));
//                if(aList.get(currentPosition).getBlock() == "Block: A") {
//                    Uri uri = Uri.parse(blockA);
//                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri ));
//                }else if(aList.get(currentPosition).getBlock() == "Block: B") {
//                    Uri uri = Uri.parse(blockB);
//                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri ));
//                }else {
//                    Uri uri = Uri.parse(blockC);
//                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri ));
//                }
            }
        });

        holder.room.setText(model.getRoom());

    }

    private void delete(String name) {
        DatabaseReference root = fbase.getReference().child("Details").child("Activities").child(name);
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        Button deletebutton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            facultyName = itemView.findViewById(R.id.facultyname);
            image = itemView.findViewById(R.id.imageView2);
            description = itemView.findViewById(R.id.description);
            block = itemView.findViewById(R.id.block);
            room = itemView.findViewById(R.id.room);
            deletebutton = itemView.findViewById(R.id.detailsdeletebutton);
        }
    }
}
