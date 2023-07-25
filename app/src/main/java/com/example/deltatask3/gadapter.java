package com.example.deltatask3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class gadapter extends RecyclerView.Adapter<tViewHolder>{
    @NonNull
    Context context;
    List<New> data;

    public gadapter(Context context, List<New> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.g_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.gname.setText(data.get(position).getNewname());
        holder.gmoney.setText(String.valueOf(data.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
