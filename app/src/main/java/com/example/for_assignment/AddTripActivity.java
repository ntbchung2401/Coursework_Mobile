package com.example.for_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class AddTripActivity extends AppCompatActivity {
    private Button button_date, add_Trip;
    private RadioGroup radioGroup;
    private RadioButton radio_yes;
    private RadioButton radio_no;
    private EditText inputNameTrip, inputDestination, inputDescription,inputDoT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        inputDoT = findViewById(R.id.inputDoT);
        button_date = findViewById(R.id.button_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                inputDoT.setText(date);
                            }
                 },year, month,day);
                datePickerDialog.show();
            }
        });
        // add Trip
        inputNameTrip = findViewById(R.id.inputNameTrip);
        inputDescription = findViewById(R.id.inputDescription);
        radioGroup = findViewById(R.id.radioGroup);
        radio_yes = findViewById(R.id.radio_yes);
        radio_no = findViewById(R.id.radio_no);
        inputDestination = findViewById(R.id.inputDestination);
        add_Trip = findViewById(R.id.add_Trip);
        Intent putIntent = new Intent(AddTripActivity.this, MainActivity.class);
        add_Trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int requireGroup = radioGroup.getCheckedRadioButtonId();
                RadioButton radioGroup = findViewById(requireGroup);
                String nameTrip = inputNameTrip.getText().toString().trim();
                String destination = inputDestination.getText().toString().trim();
                String date = inputDoT.getText().toString().trim();
                String strRequire = radioGroup.getText().toString().trim();
                String description = inputDescription.getText().toString().trim();
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddTripActivity.this);
                myDB.addTrip(nameTrip,destination,date,strRequire,description);
                startActivity(putIntent);
            }
        });
    }
}