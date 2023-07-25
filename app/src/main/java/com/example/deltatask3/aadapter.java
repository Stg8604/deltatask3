package com.example.deltatask3;

import static com.example.deltatask3.SplitActivity.cont;
import static com.example.deltatask3.SplitActivity.data4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class aadapter extends RecyclerView.Adapter<tViewHolder> {
    Context context;
    List<ldata> data;

    public aadapter(Context context, List<ldata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.a_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.aname.setText(data.get(position).getLname());
        holder.recylea.setLayoutManager(new LinearLayoutManager(cont));
        holder.recyleb.setAdapter(new padapter(cont,data.get(position).getLp()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
