package com.example.deltatask3;

import static com.example.deltatask3.LoginActivity.usethisname;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplitActivity extends AppCompatActivity {
    private float amt;
    private int count=0;
    Context context;
    private String accesstoken,nm,na;
    private Button spadd,spsave,spreset,done;
    ArrayList<tdata> data=new ArrayList<>();
    ArrayList<sdata> data2=new ArrayList<>();
    ArrayList<pdata> data3=new ArrayList<>();
    ArrayList<sdata> data5=new ArrayList<>();
    static ArrayList<pdata> data4=new ArrayList<>();
    RecyclerView recyclerView2;
    private TextView tt,am,act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        amt=getIntent().getIntExtra("amt",0);
        nm=getIntent().getStringExtra("name");
        na=getIntent().getStringExtra("Split");
        accesstoken=getIntent().getStringExtra("acc");
        tt=findViewById(R.id.tt);
        am=findViewById(R.id.amt);
        act=findViewById(R.id.actual);
        tt.setText(nm);
        am.setText("Paid By "+na);
        act.setText(String.valueOf(amt));
        spadd=findViewById(R.id.spadd);
        spsave=findViewById(R.id.spsave);
        spreset=findViewById(R.id.spreset);
        recyclerView2=findViewById(R.id.recycles);
        context=getBaseContext();
        spadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog2();
                //Toast.makeText(context, String.valueOf(data2.size()), Toast.LENGTH_SHORT).show();
            }
        });
        spreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data3=new ArrayList<>();
                data2=new ArrayList<>();
                data4=new ArrayList<>();
                recyclerView2.setLayoutManager(new LinearLayoutManager(context));
                recyclerView2.setAdapter(new sadapter(getApplicationContext(),data2));
            }
        });
        spsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://localhost:8000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Service service = retrofit.create(Service.class);
                for(int i=0;i<data5.size();i++) {
                    Call<Void> call = service.sink(nm,na,usethisname,data5.get(i).getImage(),data5.get(i).getSname(),data5.get(i).getAmt());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            int state = response.code();
                            if (response.isSuccessful()) {
                                Toast.makeText(SplitActivity.this, "success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SplitActivity.this, String.valueOf(state), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("API", "Error");
                            System.out.println(t.getMessage());
                            Toast.makeText(SplitActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void showdialog2(){
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog = new Dialog(SplitActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.d_close);
        RecyclerView recyclerView=dialog.findViewById(R.id.recyclethis);
        done=dialog.findViewById(R.id.donebtn);
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
                    Context context=getBaseContext();
                    data=response.body();
                    if(data3.isEmpty()) {
                        for (int i = 0; i < data.size(); i++) {
                            data3.add(new pdata(data.get(i).getName(), data.get(i).getImage()));
                        }
                    }
                    Toast.makeText(context, String.valueOf(data3.size()), Toast.LENGTH_SHORT).show();
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(new padapter(getApplicationContext(),data3));
                }
                else{
                    Toast.makeText(SplitActivity.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<tdata>> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(SplitActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    for(int i=0;i<data4.size();i++){
                        data2.add(new sdata(data4.get(i).getPname(), data.get(i).getImage(), (amt / data4.size())));
                    }
                recyclerView2.setLayoutManager(new LinearLayoutManager(context));
                recyclerView2.setAdapter(new sadapter(getApplicationContext(),data2));
                data5=data2;
                data2=new ArrayList<>();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}