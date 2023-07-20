package com.example.deltatask3;


import static com.example.deltatask3.SplitActivity.data4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class padapter extends RecyclerView.Adapter<tViewHolder> {
    Context context;
    List<pdata> data;

    public padapter(Context context, List<pdata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.p_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.pname.setText(data.get(position).getPname());
        holder.pbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(data.get(position));
                data4.add(data.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
