package com.example.for_assignment;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {
    private Button button_date, button_addExpense;
    private EditText inputDoT, inputAmount;
    private Spinner spinner_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        inputDoT = findViewById(R.id.inputDoT);
        inputAmount = findViewById(R.id.inputAmount);
        button_date = findViewById(R.id.button_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpenseActivity.this,
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
        spinner_type = findViewById(R.id.spinner_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_expenses, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);
        Intent intent = getIntent();
        int trip_id = Integer.parseInt(intent.getStringExtra("tripID"));
        button_addExpense = findViewById(R.id.button_addExpense);
        button_addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPut = new Intent(AddExpenseActivity.this, ExpenseActivity.class);
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddExpenseActivity.this);
                if(inputAmount.length()==0){
                    Toast.makeText(AddExpenseActivity.this, "Input Amount can't be empty", Toast.LENGTH_SHORT).show();
                }else if(inputDoT.length()==0){
                    Toast.makeText(AddExpenseActivity.this, "Input Amount can't be empty", Toast.LENGTH_SHORT).show();
                }else{
                    intentPut.putExtra("tripID", String.valueOf(trip_id));
                    String type = spinner_type.getSelectedItem().toString().trim();
                    double amount = Double.parseDouble(inputAmount.getText().toString().trim());
                    String time = inputDoT.getText().toString().trim();
                    myDB.addExpenses(type,amount,time, trip_id);
                    startActivity(intentPut);
                }
                /*if(TextUtils.isEmpty(inputAmount.getText().toString())){
                    inputAmount.setError("Fill can't be empty");
                    return;
                }else if(TextUtils.isEmpty(inputDoT.getText().toString())){
                    inputDoT.setError("Fill can't be empty");
                    return;
                }*/

            }
        });
    }
}