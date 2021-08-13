package com.example.newtrainigproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newtrainigproject.Adapter.UserRvAdapter;
import com.example.newtrainigproject.database.LoginRegistrationTable;
import com.example.newtrainigproject.Model.LoginRegModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button btAdd;
    Button btView;
    Button btActor;
    RecyclerView rvHome;
    LoginRegistrationTable mLoginRegModel;
    Context context;
    UserRvAdapter Adapter;
    List<LoginRegModel> loginRegModel=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_home);
        btAdd=findViewById(R.id.btAdd);
        btView=findViewById(R.id.btView);
        btActor=findViewById(R.id.btActor);
        rvHome=findViewById(R.id.rvHome);
        mLoginRegModel=new LoginRegistrationTable(context);
        Adapter=new UserRvAdapter(context,loginRegModel);
        rvHome.setLayoutManager(new LinearLayoutManager(context));
        rvHome.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL));
        rvHome.setAdapter(Adapter);
        loginRegModel=mLoginRegModel.getAllUsers();
        Adapter.updateAdapter(loginRegModel);
        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,ActorsListActivity.class);
                startActivity(i);
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,SpinnerActivity.class);
                startActivity(i);
            }
        });
        btActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,ActorFormActivity.class);
                startActivity(i);

            }
        });
    }

}