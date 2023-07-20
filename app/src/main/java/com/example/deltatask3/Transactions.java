package com.example.deltatask3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Transactions extends AppCompatActivity {
    private String accesstoken;
    List<tdata> data=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        recyclerView= findViewById(R.id.recylert);
        accesstoken=getIntent().getStringExtra("acc");
        gettlist();
        //data.add(new tdata("sashaank","hhh",150,"settled"));
        //data.add(new tdata("madhav","hhh",170,"settled"));
        //data.add(new tdata("karthik","hhh",270,"settle"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new tadapter(getApplicationContext(),data));
    }
    private void gettlist(){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("Authorization", "Bearer " + accesstoken);
                    Request newRequest = requestBuilder.build();
                    return chain.proceed(newRequest);
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8000/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service=retrofit.create(Service.class);
        Call<ArrayList<tdata>> call=service.getData();
        call.enqueue(new Callback<ArrayList<tdata>>() {
            @Override
            public void onResponse(Call<ArrayList<tdata>> call, Response<ArrayList<tdata>> response) {
                int status=response.code();
                if(response.isSuccessful()){
                    data=response.body();
                    recyclerView.setLayoutManager(new LinearLayoutManager(Transactions.this));
                    recyclerView.setAdapter(new tadapter(getApplicationContext(),data));
                }
                else{
                    Toast.makeText(Transactions.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<tdata>> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(Transactions.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}