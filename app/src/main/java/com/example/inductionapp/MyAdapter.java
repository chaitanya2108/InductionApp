package com.example.inductionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<ActivitiesModel> aList;
    Context context;

    public MyAdapter(Context context, ArrayList<ActivitiesModel> aList) {
        this.aList = aList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activitylist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        ActivitiesModel model = aList.get(position);
        holder.activityName.setText(model.getActivityName());
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView activityName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            activityName = itemView.findViewById(R.id.useractivityname);
        }

    }
}
