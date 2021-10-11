package com.example.inductionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;

public class MyAdminAdapter extends RecyclerView.Adapter<MyAdminAdapter.MyViewHolder>{

    Date date=new Date();
    String dayWeekText = new SimpleDateFormat("EEEE").format(date);
    private FirebaseDatabase fbase = FirebaseDatabase.getInstance();
    ArrayList<ActivitiesModel> aList;
    Context context;

    public MyAdminAdapter(Context context, ArrayList<ActivitiesModel> aList) {
        this.aList = aList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adminaxtivitylist, parent, false);
        return new MyAdminAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdminAdapter.MyViewHolder holder, int position) {

        String text = aList.get(position).getActivityName();

        ((MyAdminAdapter.MyViewHolder)holder).deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position, text);
            }
        });

        ActivitiesModel model = aList.get(position);
        holder.activityName.setText(model.getActivityName());
    }

    private void delete(int position, String name) {
        DatabaseReference root = fbase.getReference().child("Activities").child(dayWeekText).child(name);
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

        TextView activityName;
        Button deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            activityName = itemView.findViewById(R.id.useractivityname);
            deleteButton = itemView.findViewById(R.id.deletebutton);
        }
    }

}
