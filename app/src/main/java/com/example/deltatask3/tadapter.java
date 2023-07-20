package com.example.deltatask3;

import static com.example.deltatask3.LoginActivity.debt;
import static com.example.deltatask3.LoginActivity.usethisname;

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

public class tadapter extends RecyclerView.Adapter<tViewHolder>{
    Context context;
    List<tdata> data;

    public tadapter(Context context, List<tdata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public tViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new tViewHolder(LayoutInflater.from(context).inflate(R.layout.t_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull tViewHolder holder, int position) {
        holder.tname.setText(data.get(position).getName());
        holder.tmoney.setText(String.valueOf(data.get(position).getMoney()));
        holder.tsettle.setText(data.get(position).getSettle());
        if(data.get(position).getSettle().equals("settle")){
            holder.tmoney.setTextColor(Color.RED);
            holder.tsettle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    debt=debt-data.get(position).getMoney();
                    holder.tmoney.setTextColor(Color.BLACK);
                    holder.tsettle.setText("settled");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://localhost:8000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Service service = retrofit.create(Service.class);
                    Call<Void> call = service.send(debt,usethisname,data.get(position).getName());
                    //Call<Data> call=service.sendData(user);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            int state=response.code();
                            if (response.isSuccessful()) {
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                                //accesstoken=response.body().getAccessToken();
                                //Toast.makeText(LoginActivity.this, accesstoken, Toast.LENGTH_SHORT).show();
                               // getall();

                            } else {
                                //Toast.makeText(this,String.valueOf(state), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("API","Error");
                            System.out.println(t.getMessage());
                            Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        else if(data.get(position).getSettle().equals("owes you")){
            holder.tmoney.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
