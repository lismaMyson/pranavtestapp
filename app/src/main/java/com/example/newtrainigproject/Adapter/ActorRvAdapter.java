package com.example.newtrainigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtrainigproject.Model.ModelSpinner;
import com.example.newtrainigproject.R;

import java.util.ArrayList;
import java.util.List;

public class ActorRvAdapter extends RecyclerView.Adapter<ActorRvAdapter.ViewHolder> {
    Context context;
    List <ModelSpinner> modelSpinner=new ArrayList<>();
    public ActorRvAdapter(Context context,List<ModelSpinner> modelSpinner){
        this.context=context;
        this.modelSpinner=modelSpinner;
    }
    public void updateAdapter(List<ModelSpinner>modelSpinner){
        this.modelSpinner=modelSpinner;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.actors_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvSlNumber.setText(String.valueOf(position+1));
        holder.tvActors.setText(modelSpinner.get(position).getActors());
        holder.tvFilm.setText(modelSpinner.get(position).getFilm());

    }

    @Override
    public int getItemCount() {return modelSpinner.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSlNumber;
        TextView tvActors;
        TextView tvFilm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSlNumber=itemView.findViewById(R.id.tvSlNumber);
            tvActors=itemView.findViewById(R.id.tvActors);
            tvFilm=itemView.findViewById(R.id.tvFilm);


        }
    }
}
