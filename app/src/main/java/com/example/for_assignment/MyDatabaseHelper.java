package com.example.for_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "coursework.db";
    public static final String TABLE_TRIP = "my_trip";
    public static final String TABLE_EXPENSES = "trip_expenses";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String trips_table = "CREATE TABLE "+TABLE_TRIP+ "(trip_id INTEGER primary key autoincrement," +
                " nameTrip TEXT, destination TEXT, " +"date TEXT, require TEXT, description TEXT);";
        db.execSQL(trips_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TRIP);
        onCreate(db);
    }

    public void addTrip( String nameTrip, String destination,String date, String require, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nameTrip", nameTrip);
        cv.put("destination", destination);
        cv.put("date", date);
        cv.put("require", require);
        cv.put("description", description);
        long result = db.insert(TABLE_TRIP, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to Add", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully to Add", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor showAllTrip(){
        String query ="SELECT * FROM "+ TABLE_TRIP;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
}
