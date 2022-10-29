package com.example.for_assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {
    private Context context;
    private ArrayList trip_id, nameTrip, destination, date, require, description;

    TripAdapter(Context context, ArrayList trip_id, ArrayList nameTrip, ArrayList destination,
                ArrayList date, ArrayList require, ArrayList description){
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.nameTrip_txt.setText(String.valueOf(nameTrip.get(position)));
        holder.destination_txt.setText(String.valueOf(destination.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.require_txt.setText(String.valueOf(require.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trip_id_txt, nameTrip_txt, destination_txt, date_txt, require_txt, description_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            nameTrip_txt = itemView.findViewById(R.id.nameTrip_txt);
            destination_txt = itemView.findViewById(R.id.destination_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            require_txt = itemView.findViewById(R.id.require_txt);
            description_txt = itemView.findViewById(R.id.description_txt);
        }
    }
}
