package com.example.deltatask3;

import static com.example.deltatask3.SplitActivity.amt;
import static com.example.deltatask3.SplitActivity.cont;
import static com.example.deltatask3.SplitActivity.recyclerView2;
import static com.example.deltatask3.SplitHistory.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class radapter extends RecyclerView.Adapter<tViewHolder> {
    Context context;
    List<rdata> data;

    public radapter(Context context, List<rdata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.i_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.iname.setText(data.get(position).getGname());
        holder.recyleg.setLayoutManager(new LinearLayoutManager(cont));
        holder.recyleg.setAdapter(new iadapter(cont,data.get(position).getRarray()));
        holder.ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<sdata> news=new ArrayList<>();
                ArrayList<pdata> pnews=data.get(position).getRarray();
                for(int i=0;i<pnews.size();i++){
                    news.add(new sdata(pnews.get(i).getPname(),pnews.get(i).getPimage(),amt/pnews.size()));
                }
                recyclerView2.setLayoutManager(new LinearLayoutManager(context));
                recyclerView2.setAdapter(new sadapter(cont,news));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
