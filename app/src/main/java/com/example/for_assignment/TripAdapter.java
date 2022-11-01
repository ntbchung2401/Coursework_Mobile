package com.example.for_assignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> implements Filterable{
    private Context context;
    private Activity activity;
     private List<Trip> trips = new ArrayList<>();
    private List<Trip> tripFind;
    private ArrayList trip_id, nameTrip, destination, date, require, description;

    TripAdapter(Activity activity, Context context, List<Trip> trips){
        this.activity = activity;
        this.context = context;
        this.trips = trips;
        this.tripFind = trips;
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
        Trip trip = trips.get(position);
        holder.trip_id_txt.setText(String.valueOf(trip.getId()));
        holder.nameTrip_txt.setText(String.valueOf(trip.getName()));
        holder.destination_txt.setText(String.valueOf(trip.getDestination()));
        holder.date_txt.setText(String.valueOf(trip.getDate()));
        holder.require_txt.setText(String.valueOf(trip.getRequire()));
        holder.description_txt.setText(String.valueOf(trip.getDescription()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateTripActivity.class);
                intent.putExtra("id", String.valueOf(trip.getId()));
                intent.putExtra("name", String.valueOf(trip.getName()));
                intent.putExtra("destination", String.valueOf(trip.getDestination()));
                intent.putExtra("date", String.valueOf(trip.getDate()));
                intent.putExtra("require", String.valueOf(trip.getRequire()));
                intent.putExtra("description", String.valueOf(trip.getDescription()));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
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
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    trips = tripFind;
                }else {
                    List<Trip> list = new ArrayList<>();
                    for(Trip trip:tripFind){
                        if(trip.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(trip);
                        }
                    }
                    trips = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = trips;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                trips = (List<Trip>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
