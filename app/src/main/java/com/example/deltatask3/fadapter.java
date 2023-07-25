package com.example.deltatask3;

import static com.example.deltatask3.SplitActivity.data4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class fadapter extends RecyclerView.Adapter<tViewHolder> {
    Context context;
    List<odata> data;

    public fadapter(Context context, List<odata> data) {
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
        holder.fname.setText(data.get(position).getOname());
        holder.fperson.setText(data.get(position).getOperson());
        holder.famount.setText(String.valueOf(data.get(position).getAmt()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
