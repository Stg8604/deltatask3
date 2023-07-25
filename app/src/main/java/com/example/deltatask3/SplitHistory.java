package com.example.deltatask3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplitHistory extends AppCompatActivity {
    ArrayList<kdata> kdate=new ArrayList<>();
    ArrayList<hdata> hdate=new ArrayList<>();
    String accesstoken;
    RecyclerView recycleyou;
    static Context content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_history);
        accesstoken=getIntent().getStringExtra("acc");
        getyes();
        recycleyou=findViewById(R.id.recycleyou);
        content=getBaseContext();
    }
    private void getyes(){
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
        Call<ArrayList<kdata>> call=service.getKdata();
        call.enqueue(new Callback<ArrayList<kdata>>() {
            @Override
            public void onResponse(Call<ArrayList<kdata>> call, Response<ArrayList<kdata>> response) {
                int status=response.code();
                if(response.isSuccessful()){
                    Context context=getBaseContext();
                    int current=0;
                    Toast.makeText(SplitHistory.this, "Success", Toast.LENGTH_SHORT).show();
                    kdate=response.body();
                    ArrayList<New> ne=new ArrayList<>();
                    for(int i=0;i<kdate.size();i++){
                        if(i==0||kdate.get(i).getSpname().equals(kdate.get(i-1).getSpname())){
                            ne.add(new New(kdate.get(i).getUsername(), kdate.get(i).getImage(), kdate.get(i).getName(), kdate.get(i).getAmount2()));
                        }
                        else{
                            hdate.add(new hdata(kdate.get(i-1).getSpname(), kdate.get(i-1).getSpperson(), ne));
                            ne=new ArrayList<>();
                            ne.add(new New(kdate.get(i).getUsername(), kdate.get(i).getImage(), kdate.get(i).getName(), kdate.get(i).getAmount2()));
                        }
                        if(i==kdate.size()-1){
                            hdate.add(new hdata(kdate.get(i).getSpname(), kdate.get(i).getSpperson(), ne));
                        }
                        /*if(hdate.isEmpty()||kdate.get(i).getSpname()!=hdate.get(current-1).getSpname()) {
                            ne=new ArrayList<>();
                            ne.add(new New(kdate.get(i).getUsername(), kdate.get(i).getImage(), kdate.get(i).getName(), kdate.get(i).getAmount2()));
                        }
                        else{
                            ne.add(new New(kdate.get(i).getUsername(), kdate.get(i).getImage(), kdate.get(i).getName(), kdate.get(i).getAmount2()));
                        }
                        if(hdate.isEmpty()||kdate.get(i).getSpname()!=hdate.get(current-1).getSpname()) {
                            hdate.add(new hdata(kdate.get(i).getSpname(), kdate.get(i).getSpperson(), ne));
                            current+=1;
                        }
                        else{
                            hdate.remove(hdate.get(current-1));
                            hdate.add(new hdata(kdate.get(i).getSpname(), kdate.get(i).getSpperson(), ne));
                        }*/
                    }
                    recycleyou.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recycleyou.setAdapter(new hadapter(getApplicationContext(),hdate));
                }
                else{
                    Toast.makeText(SplitHistory.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<kdata>> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(SplitHistory.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}