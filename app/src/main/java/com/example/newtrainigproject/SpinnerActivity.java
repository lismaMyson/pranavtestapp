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

import com.example.newtrainigproject.Adapter.ActorRvAdapter;
import com.example.newtrainigproject.Model.FormActorModel;
import com.example.newtrainigproject.Model.ModelSpinner;
import com.example.newtrainigproject.database.FilmActorsMainTable;
import com.example.newtrainigproject.database.FormActorSubTable;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] actors={"MohanLal","Mamooty","Dileep","AlluArjun"};
    EditText etFavFilm;
    Button btSub;
    Context context;
    FilmActorsMainTable filmActorsMainTable;
    FormActorSubTable mFormActorSubTable;
    String spValue;
    List<FormActorModel>formActorModelList= new ArrayList<>();
    List<String>formActors=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        filmActorsMainTable =new FilmActorsMainTable(context);
        setContentView(R.layout.activity_spinner);
        mFormActorSubTable=new FormActorSubTable(context);
        formActorModelList=mFormActorSubTable.getAllActors();
        etFavFilm=findViewById(R.id.etFavFilm);
        btSub=findViewById(R.id.btSub);
        Spinner spinner=(Spinner) findViewById(R.id.spActors);
        spinner.setOnItemSelectedListener(SpinnerActivity.this);
        for(int i=0;i<formActorModelList.size();i++){
            FormActorModel modelSpinner=formActorModelList.get(i);
            formActors.add(modelSpinner.getActors());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_spinner_item,formActors);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);

        btSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFavFilm==null||etFavFilm.getText().toString().trim().equals("")){
                    toastCommon("Enter a valid film");
                }else if(filmActorsMainTable.checkActor(spValue,etFavFilm.getText().toString().trim())){
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
        filmActorsMainTable.insertIntoLogin(modelSpinner);

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