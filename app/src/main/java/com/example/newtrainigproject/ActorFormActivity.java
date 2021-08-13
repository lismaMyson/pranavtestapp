package com.example.newtrainigproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newtrainigproject.Model.FormActorModel;
import com.example.newtrainigproject.database.FormActorSubTable;

public class ActorFormActivity extends AppCompatActivity  {
    EditText etFormActor;
    Button btFormEnter;
    Context context;
    FormActorSubTable formActorSubTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_actor_form);
        formActorSubTable= new FormActorSubTable(context);
        etFormActor=findViewById(R.id.etFormActor);
        btFormEnter=findViewById(R.id.btFormEnter);
        btFormEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etFormActor==null || etFormActor.getText().toString().trim().equals("")){
                toastCommon("Enter your favorite Actor");
            }
                else {
                   toastCommon("success");

                }
                Insert();
                clearForm();

            }
            private void Insert(){
                FormActorModel mFormActorModel=new FormActorModel();
                mFormActorModel.setActors(etFormActor.getText().toString());
              long l=formActorSubTable.insertIntoFormActor(mFormActorModel);
                Log.d("log","insert"+l);

            }private void clearForm(){
                etFormActor.setText("");
            }
        });



    }private void toastCommon(String  message){
        Toast t=Toast.makeText(ActorFormActivity.this,message,Toast.LENGTH_LONG);
        t.show();
    }

}