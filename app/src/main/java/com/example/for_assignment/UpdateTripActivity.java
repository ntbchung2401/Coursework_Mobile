package com.example.for_assignment;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateTripActivity extends AppCompatActivity {
    private EditText name_input, destination_input, date_input, require_input, description_input;
    private Button update_button, button_date,delete_trip, show_expense;
    private RadioGroup radioGroup;
    private RadioButton radio_yes;
    private RadioButton radio_no;
    String trip_id,name,destination,date, require, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip);

        button_date = findViewById(R.id.button_date);
        delete_trip = findViewById(R.id.delete_trip);
//        Intent intentX = getIntent();
//        int tripID = Integer.parseInt(intentX.getStringExtra("trip_id"));
        show_expense = findViewById(R.id.show_expense);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateTripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                date_input.setText(date);
                            }
                        },year, month,day);
                datePickerDialog.show();
            }
        });


        name_input = findViewById(R.id.inputNameTrip2);
        destination_input = findViewById(R.id.inputDestination2);
        date_input = findViewById(R.id.inputDoT2);
        description_input = findViewById(R.id.inputDescription2);
        update_button = findViewById(R.id.update_trip);
        radioGroup = findViewById(R.id.radioGroup);
        radio_yes = findViewById(R.id.radio_yes);
        radio_no = findViewById(R.id.radio_no);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }
        Intent putIntent = new Intent(UpdateTripActivity.this, MainActivity.class);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int requireGroup = radioGroup.getCheckedRadioButtonId();
                RadioButton radioGroup = findViewById(requireGroup);
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateTripActivity.this);
                name = name_input.getText().toString().trim();
                destination = destination_input.getText().toString().trim();
                date = date_input.getText().toString().trim();

                require = radioGroup.getText().toString().trim();
                description = description_input.getText().toString().trim();
                myDB.updateTripData(trip_id,name,destination,date,require,description);
                startActivity(putIntent);
            }
        });
        delete_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        show_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentE = new Intent(UpdateTripActivity.this, ExpenseActivity.class);
                intentE.putExtra("tripID", String.valueOf(trip_id));
                startActivity(intentE);
            }
        });

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&getIntent().hasExtra("destination") &&getIntent().hasExtra("date") &&getIntent().hasExtra("require") &&getIntent().hasExtra("description")){
            //getting
            trip_id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            require = getIntent().getStringExtra("require");
            description = getIntent().getStringExtra("description");
            //setting
            name_input.setText(name);
            destination_input.setText(destination);
            date_input.setText(date);
            if (require.equals("Yes"))
            {
                radio_yes.setChecked(true);
                radio_no.setChecked(false);
            }
            else {

                radio_yes.setChecked(false);
                radio_no.setChecked(true);
            }
//            require_input.setText(require);
            description_input.setText(description);
        }else{
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }
    }


    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+ name +" ?");
        builder.setMessage("Are you sure to delete "+name+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateTripActivity.this);
                myDB.deleteATrip(trip_id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}