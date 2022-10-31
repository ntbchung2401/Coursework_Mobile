package com.example.for_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExpenseActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<String> expenses_id, expenses_type,expenses_amount, expenses_time;
    ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Intent intent = getIntent();
        int tripID = Integer.parseInt(intent.getStringExtra("tripID"));
        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseActivity.this, AddExpenseActivity.class);
                intent.putExtra("trip_id", String.valueOf(tripID));
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(ExpenseActivity.this);
        expenses_id = new ArrayList<>();
        expenses_type = new ArrayList<>();
        expenses_amount = new ArrayList<>();
        expenses_time = new ArrayList<>();
        storeDataInArray(tripID);

        expenseAdapter = new ExpenseAdapter(ExpenseActivity.this,expenses_id, expenses_type,expenses_amount,expenses_time,tripID);
        recyclerView.setAdapter(expenseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExpenseActivity.this));
    }

    void storeDataInArray(int tripID){
        Cursor cursor = myDB.readAllExpenses(tripID);
        if(cursor.getCount() == 0){
            Toast.makeText(ExpenseActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                expenses_id.add(cursor.getString(0));
                expenses_type.add(cursor.getString(1));
                expenses_amount.add(cursor.getString(2));
                expenses_time.add(cursor.getString(3));
            }
        }
    }


}