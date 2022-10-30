package com.example.for_assignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList trip_id, nameTrip, destination, date, require, description;

    TripAdapter(Activity activity, Context context, ArrayList trip_id, ArrayList nameTrip, ArrayList destination,
                ArrayList date, ArrayList require, ArrayList description){
        this.activity = activity;
        this.context = context;
        this.trip_id = trip_id;
        this.nameTrip = nameTrip;
        this.destination = destination;
        this.date = date;
        this.require = require;
        this.description = description;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.nameTrip_txt.setText(String.valueOf(nameTrip.get(position)));
        holder.destination_txt.setText(String.valueOf(destination.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.require_txt.setText(String.valueOf(require.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateTripActivity.class);
                intent.putExtra("id", String.valueOf(trip_id.get(position)));
                intent.putExtra("name", String.valueOf(nameTrip.get(position)));
                intent.putExtra("destination", String.valueOf(destination.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("require", String.valueOf(require.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trip_id_txt, nameTrip_txt, destination_txt, date_txt, require_txt, description_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            nameTrip_txt = itemView.findViewById(R.id.nameTrip_txt);
            destination_txt = itemView.findViewById(R.id.destination_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            require_txt = itemView.findViewById(R.id.require_txt);
            description_txt = itemView.findViewById(R.id.description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
