package com.example.newtrainigproject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newtrainigproject.database.LoginRegistrationdb;
import com.example.newtrainigproject.database.RegModel.LoginRegModel;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    String gender = "nothing";
    String hobbies="";
    EditText etName;
    EditText editPass;
    EditText etEmail;
    EditText etphNumber;
    EditText etAge;
    EditText etDob;
    CheckBox cbDrawing;
    CheckBox cbSinging;
    CheckBox cbDancing;
    CheckBox cbAgree;
    Button btSubmit;
    Button btReset;
    RadioGroup rbGender;
    RadioButton rbMale;
    RadioButton rbFemale;
    Calendar myCalender = Calendar.getInstance();
    Context context;
    LoginRegistrationdb loginRegistrationdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;
        loginRegistrationdb = new LoginRegistrationdb(context);
        etName = findViewById(R.id.etName);
        editPass = findViewById(R.id.editPass);
        etEmail = findViewById(R.id.etEmail);
        etphNumber = findViewById(R.id.etPhNumber);
        etAge = findViewById(R.id.etAge);
        etDob = findViewById(R.id.etDob);
        cbDrawing = findViewById(R.id.cbDrawing);
        rbMale=findViewById(R.id.rbMale);
        rbFemale=findViewById(R.id.rbFemale);
        cbSinging = findViewById(R.id.cbSinging);
        cbDancing = findViewById(R.id.cbDancing);
        cbAgree = findViewById(R.id.cbAgree);
        btSubmit = findViewById(R.id.btSubmit);
        btReset=findViewById(R.id.btReset);
        rbGender = findViewById(R.id.rbGender);
        rbGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbMale) {
                    toastCommon("male is selected");
                    gender = "male";
                }
                if (i == R.id.rbFemale) {
                    toastCommon("female is selected");
                    gender = "female";
                }
            }
        });
        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        myCalender.set(Calendar.DAY_OF_MONTH, day);
                        myCalender.set(Calendar.MONTH, month);
                        myCalender.set(Calendar.YEAR, year);
                        etDob.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });


        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName == null || etName.getText().toString().trim().equals("") || etName.getText().toString().length() < 3) {
                    toastCommon("Enter a valid name");
                } else if (editPass.getText().toString().trim().equals("") || editPass.getText().toString().length() < 6) {
                    toastCommon("Enter a valid password");
                } else if (etEmail.getText().toString().trim().equals("")) {
                    toastCommon("Enter a valid email");
                } else if (etphNumber.getText().toString().trim().equals("") || etphNumber.getText().toString().length() < 10) {
                    toastCommon("Enter a valid phone number");
                } else if (!cbAgree.isChecked()) {
                    toastCommon("Plz agree terms and conditions");
                } else {
                    toastCommon("Registered successfully");
                    if (cbDrawing.isChecked()) {
                        toastCommon("drawing selected");
                        hobbies += "drawing";
                    }
                    if (cbSinging.isChecked()) {
                        toastCommon("singing selected");
                        hobbies += "singing";
                    }
                    if (cbDancing.isChecked()) {
                        toastCommon("dancing selected");
                        hobbies += "dancing";
                    }
                    register();
                    clearForm();
                }
            }

        });
    }
    private  void clearForm(){
        etName.setText("");
        editPass.setText("");
        etEmail.setText("");
        etphNumber.setText("");
        etAge.setText("");
        etDob.setText("");
        cbDrawing.setChecked(false);
        cbSinging.setChecked(false);
        cbDancing.setChecked(false);
        rbMale.setChecked(false);
        rbFemale.setChecked(false);
        cbAgree.setChecked(false);
    }
    private void register() {
        LoginRegModel mLoginRegModel=new LoginRegModel();
        mLoginRegModel.setUname(etEmail.getText().toString());
        mLoginRegModel.setName(etName.getText().toString());
        mLoginRegModel.setPassword(editPass.getText().toString());
        mLoginRegModel.setAge(etAge.getText().toString());
        mLoginRegModel.setDate_of_birth(etDob.getText().toString());
        mLoginRegModel.setGender(gender);
        mLoginRegModel.setHobbies(hobbies);
        mLoginRegModel.setPh_number(etphNumber.getText().toString());
        mLoginRegModel.setEmail(etEmail.getText().toString());
        loginRegistrationdb.insertIntoLogin(mLoginRegModel);
    }
    private void toastCommon(String message) {
        Toast t = Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG);
        t.show();
    }
}