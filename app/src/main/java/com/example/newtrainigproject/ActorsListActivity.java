package com.example.newtrainigproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.newtrainigproject.Adapter.ActorRvAdapter;
import com.example.newtrainigproject.Model.ModelSpinner;
import com.example.newtrainigproject.database.FilmActorsMainTable;

import java.util.ArrayList;
import java.util.List;

public class ActorsListActivity extends AppCompatActivity {
    RecyclerView rvActorsList;
    FilmActorsMainTable filmActorsMainTable;
    Context context;
    ActorRvAdapter Adapter;
    List<ModelSpinner> modelSpinner=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_actors_list);
        rvActorsList=findViewById(R.id.rvActorsList);
        filmActorsMainTable =new FilmActorsMainTable(context);
        Adapter=new ActorRvAdapter(context,modelSpinner);
        rvActorsList.setLayoutManager(new LinearLayoutManager(context));
        rvActorsList.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        rvActorsList.setAdapter(Adapter);
        modelSpinner= filmActorsMainTable.getAllUsers();
        Adapter.updateAdapter(modelSpinner);

    }
}