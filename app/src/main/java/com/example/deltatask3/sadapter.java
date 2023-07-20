package com.example.deltatask3;

import static com.example.deltatask3.LoginActivity.debt;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sadapter extends RecyclerView.Adapter<tViewHolder> {
    Context context;
    List<sdata> data;

    public sadapter(Context context, List<sdata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.s_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.sname.setText(data.get(position).getSname());
        holder.smoney.setText(String.valueOf(data.get(position).getAmt()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
