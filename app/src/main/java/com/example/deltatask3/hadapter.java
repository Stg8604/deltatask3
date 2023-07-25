package com.example.deltatask3;

import static com.example.deltatask3.SplitHistory.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class hadapter extends RecyclerView.Adapter<tViewHolder> {
    @NonNull
    Context context;
    List<hdata> data;

    public hadapter(Context context, List<hdata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.h_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position){
        holder.hname.setText("Paid By:"+data.get(position).getSpperson());
        holder.htitle.setText(data.get(position).getSpname());
        holder.recycle.setLayoutManager(new LinearLayoutManager(content));
        holder.recycle.setAdapter(new gadapter(context,data.get(position).getGdata()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
