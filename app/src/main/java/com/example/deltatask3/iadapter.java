package com.example.deltatask3;

import static com.example.deltatask3.SplitActivity.data4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class iadapter extends RecyclerView.Adapter<tViewHolder> {
    Context context;
    List<pdata> data;

    public iadapter(Context context, List<pdata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.j_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.jname.setText(data.get(position).getPname());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
