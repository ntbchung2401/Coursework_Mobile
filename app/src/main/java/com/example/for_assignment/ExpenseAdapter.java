package com.example.for_assignment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    private Context context;
    private ArrayList expenses_id, type, amount, time;
    int tripID;
    ExpenseAdapter(Context context, ArrayList expenses_id, ArrayList type, ArrayList amount, ArrayList time, int tripID){
        this.context = context;
        this.expenses_id = expenses_id;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.tripID = tripID;
    }
    @NonNull
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_expense, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.MyViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.id_expenses_txt.setText(String.valueOf(expenses_id.get(position)));
        holder.type_expenses_txt.setText(String.valueOf(type.get(position)));
        holder.amount_expenses_txt.setText(String.valueOf(amount.get(position)));
        holder.time_expenses_txt.setText(String.valueOf(time.get(position)));

    }

    @Override
    public int getItemCount() {
        return expenses_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_expenses_txt, type_expenses_txt, time_expenses_txt, amount_expenses_txt;
        LinearLayout mainLayout2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_expenses_txt = itemView.findViewById(R.id.id_expenses_txt);
            type_expenses_txt = itemView.findViewById(R.id.type_expenses_txt);
            time_expenses_txt = itemView.findViewById(R.id.time_expenses_txt);
            amount_expenses_txt = itemView.findViewById(R.id.amount_expenses_txt);
            mainLayout2 = itemView.findViewById(R.id.mainLayout2);
        }
    }
}
