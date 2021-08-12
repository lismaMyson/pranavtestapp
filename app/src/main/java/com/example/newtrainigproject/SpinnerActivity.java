package com.example.newtrainigproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newtrainigproject.Model.ModelSpinner;
import com.example.newtrainigproject.database.FilmActorsTable;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] actors={"MohanLal","Mamooty","Dileep","AlluArjun"};
    EditText etFavFilm;
    Button btSub;
    Context context;
    FilmActorsTable filmActorsTable;
    String spValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        filmActorsTable =new FilmActorsTable(context);
        setContentView(R.layout.activity_spinner);
        etFavFilm=findViewById(R.id.etFavFilm);
        btSub=findViewById(R.id.btSub);
        Spinner spinner=(Spinner) findViewById(R.id.spActors);
        spinner.setOnItemSelectedListener(SpinnerActivity.this);
        ArrayAdapter arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_spinner_item,actors);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        btSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFavFilm==null||etFavFilm.getText().toString().trim().equals("")){
                    toastCommon("Enter a valid film");
                }else if(filmActorsTable.checkActor(spValue,etFavFilm.getText().toString().trim())){
                    toastCommon("film name already added");
                } else {
                    toastCommon("success");

                }
                film();
                clearForm();
            }
        });


    }private void clearForm(){
        etFavFilm.setText("");

    }
    private void film(){
        ModelSpinner modelSpinner=new ModelSpinner();
        modelSpinner.setActors(spValue);
        modelSpinner.setFilm(etFavFilm.getText().toString());
        filmActorsTable.insertIntoLogin(modelSpinner);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),actors[i], Toast.LENGTH_SHORT).show();
        spValue=actors[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void toastCommon(String messege){
        Toast t=Toast.makeText(SpinnerActivity.this,messege,Toast.LENGTH_LONG);
        t.show();
    }
}