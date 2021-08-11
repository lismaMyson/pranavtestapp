package com.example.newtrainigproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.newtrainigproject.Adapter.UserRvAdapter;
import com.example.newtrainigproject.database.LoginRegistrationdb;
import com.example.newtrainigproject.database.RegModel.LoginRegModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvHome;
    LoginRegistrationdb mLoginRegModel;
    Context context;
    UserRvAdapter Adapter;
    List<LoginRegModel> loginRegModel=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_home);
        rvHome=findViewById(R.id.rvHome);
        mLoginRegModel=new LoginRegistrationdb(context);
        Adapter=new UserRvAdapter(context,loginRegModel);
        rvHome.setLayoutManager(new LinearLayoutManager(context));
        rvHome.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL));
        rvHome.setAdapter(Adapter);
        loginRegModel=mLoginRegModel.getAllUsers();
        Adapter.updateAdapter(loginRegModel);
    }
}