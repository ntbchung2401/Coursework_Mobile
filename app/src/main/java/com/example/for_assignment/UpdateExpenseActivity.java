package com.example.for_assignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateExpenseActivity extends AppCompatActivity {
    private EditText amount_expenses, inputDoT2;
    private Button button_date2, update_button;
    private String expenses_id,type,amount,time;
    private Spinner spinner_type2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);


        Intent intent = getIntent();
        int tripID = Integer.parseInt(intent.getStringExtra("tripID"));

        amount_expenses = findViewById(R.id.inputAmount2);
        spinner_type2 = findViewById(R.id.spinner_type2);
        inputDoT2 = findViewById(R.id.inputDoT2);
        button_date2 = findViewById(R.id.button_date2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_expenses, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner_type2.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        button_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateExpenseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                inputDoT2.setText(date);
                            }
                        },year, month,day);
                datePickerDialog.show();
            }
        });
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(type);
        }
        update_button = findViewById(R.id.button_updateExpense);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateExpenseActivity.this);
                Intent intent = new Intent(UpdateExpenseActivity.this, ExpenseActivity.class);
                if(amount_expenses.length()==0){
                    Toast.makeText(UpdateExpenseActivity.this, "Input Amount can't be empty", Toast.LENGTH_SHORT).show();
                }else if(inputDoT2.length()==0){
                    Toast.makeText(UpdateExpenseActivity.this, "Input Amount can't be empty", Toast.LENGTH_SHORT).show();
                }else {
                    intent.putExtra("tripID", String.valueOf(tripID));
                    type = spinner_type2.getSelectedItem().toString().trim();
                    amount = amount_expenses.getText().toString().trim();
                    time = inputDoT2.getText().toString().trim();
                /*if(TextUtils.isEmpty(amount_expenses.getText().toString())){
                    amount_expenses.setError("Fill can't be empty");
                    return;
                }else if(TextUtils.isEmpty(inputDoT2.getText().toString())){
                    inputDoT2.setError("Fill can't be empty");
                    return;
                }*/
                    myDB.updateExpenseData(expenses_id, type, amount, time, tripID);
                    startActivity(intent);
                }
            }
        });

    }
    public void getAndSetIntentData() {
        if(getIntent().hasExtra("expenses_id")&&getIntent().hasExtra("type")&&getIntent().hasExtra("time")){
            expenses_id = getIntent().getStringExtra("expenses_id");
            type = getIntent().getStringExtra("type");
            amount = getIntent().getStringExtra("amount");
            time = getIntent().getStringExtra("time");
            amount_expenses.setText(amount);
            inputDoT2.setText(time);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    }