package com.example.for_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.for_assignment.trip.AddTripActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<String> trip_id, nameTrip, destination, date, require, description;
    TripAdapter tripAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTripActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        trip_id = new ArrayList<>();
        nameTrip = new ArrayList<>();
        destination = new ArrayList<>();
        date = new ArrayList<>();
        require = new ArrayList<>();
        description = new ArrayList<>();

        storeDataInArrays();

        tripAdapter = new TripAdapter(MainActivity.this, trip_id,nameTrip,destination,date,require,description);
        recyclerView.setAdapter(tripAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                trip_id.add(cursor.getString(0));
                nameTrip.add(cursor.getString(1));
                destination.add(cursor.getString(2));
                date.add(cursor.getString(3));
                require.add(cursor.getString(4));
                description.add(cursor.getString(5));
            }
        }
    }

}