package com.example.newtrainigproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtrainigproject.R;
import com.example.newtrainigproject.Model.LoginRegModel;

import java.util.ArrayList;
import java.util.List;

public class UserRvAdapter extends RecyclerView.Adapter<UserRvAdapter.ViewHolder> {
    Context context;
    List<LoginRegModel> modelList = new ArrayList<>();

    public UserRvAdapter(Context context, List<LoginRegModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    public void updateAdapter(List<LoginRegModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public UserRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_view_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UserRvAdapter.ViewHolder holder, int position) {
        holder.tvSlNo.setText(String.valueOf(position + 1));
        holder.tvName.setText(modelList.get(position).getName());
        holder.tvEmail.setText(modelList.get(position).getName());
        holder.tvAge.setText(modelList.get(position).getAge());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSlNo;
        TextView tvName;
        TextView tvEmail;
        TextView tvAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSlNo = itemView.findViewById(R.id.tvSlNo);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvAge = itemView.findViewById(R.id.tvAge);
        }
    }
}
