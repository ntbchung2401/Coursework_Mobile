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

    MyDatabaseHelper(@Nullable Context context) {
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

    public void addTrip(String nameTrip, String destination, String date, String require, String description){
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
    Cursor readAllData(){
        String query = "SELECT * FROM "+ TABLE_TRIP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!= null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    public void updateTripData(String row_id, String name,String destination, String date, String require, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nameTrip", name);
        cv.put("destination", destination);
        cv.put("date", date);
        cv.put("require", require);
        cv.put("description", description);
        long result = db.update(TABLE_TRIP, cv, "trip_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully to Update", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteATrip(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_TRIP, "trip_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Delete Fail",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Delete",Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteAllTrip(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TRIP);
    }
}
