package com.example.deltatask3;

import static com.example.deltatask3.LoginActivity.usethisname;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplitActivity extends AppCompatActivity {
    static float amt;
    private int count=0;
    Context context;
    static Context cont;
    private String accesstoken,nm,na;
    private Button spadd,spsave,spreset,done,cr,se,gs;
    ArrayList<ldata> data10=new ArrayList<>();
    ArrayList<adata> data9=new ArrayList<>();
    ArrayList<rdata> data8=new ArrayList<>();
    ArrayList<idata> data7=new ArrayList<>();
    ArrayList<idata> data6=new ArrayList<>();
    ArrayList<tdata> data=new ArrayList<>();
    ArrayList<sdata> data2=new ArrayList<>();
    ArrayList<pdata> data3=new ArrayList<>();
    ArrayList<sdata> data5=new ArrayList<>();
    static ArrayList<pdata> data4=new ArrayList<>();
    static RecyclerView recyclerView2;
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
        cont=getBaseContext();
        am=findViewById(R.id.amt);
        act=findViewById(R.id.actual);
        tt.setText(nm);
        am.setText("Paid By "+na);
        act.setText(String.valueOf(amt));
        spadd=findViewById(R.id.spadd);
        spsave=findViewById(R.id.spsave);
        spreset=findViewById(R.id.spreset);
        cr=findViewById(R.id.cr);
        se=findViewById(R.id.se);
        gs=findViewById(R.id.gs);
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
        cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog3();
            }
        });
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog4();
            }
        });
        gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog5();
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
                            data3.add(new pdata(data.get(i).getName(), data.get(i).getImage(),"add"));
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
    private void showdialog3(){
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog = new Dialog(SplitActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.d_close2);
        RecyclerView recyclerView=dialog.findViewById(R.id.irecycle);
        done=dialog.findViewById(R.id.idone);
        EditText edtye=dialog.findViewById(R.id.entergg);
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
                            data3.add(new pdata(data.get(i).getName(), data.get(i).getImage(),"add"));
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
                    data6.add(new idata(usethisname,edtye.getText().toString(), data.get(i).getImage(),data4.get(i).getPname()));
                }
                for(int i=0;i<data6.size();i++) {
                    Call<Void> call = service.spl(data6.get(i).getUsername(),data6.get(i).getGname(),data6.get(i).getName(),data6.get(i).getImage());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            int status = response.code();
                            if (response.isSuccessful()) {
                                //Toast.makeText(SplitActivity.this, "success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SplitActivity.this, String.valueOf(status), Toast.LENGTH_SHORT).show();
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
                data4=new ArrayList<>();
                data6=new ArrayList<>();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void showdialog4(){
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog = new Dialog(SplitActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.d_open2);
        RecyclerView recyclerView=dialog.findViewById(R.id.recycleg);
        done=dialog.findViewById(R.id.idone);
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
        Call<ArrayList<idata>> call=service.getIdata();
        call.enqueue(new Callback<ArrayList<idata>>() {
            @Override
            public void onResponse(Call<ArrayList<idata>> call, Response<ArrayList<idata>> response) {
                int status=response.code();
                if(response.isSuccessful()){
                    Context context=getBaseContext();
                    data7=response.body();
                    Toast.makeText(context, data7.get(0).getGname(), Toast.LENGTH_SHORT).show();
                    ArrayList<pdata> ne=new ArrayList<>();
                    for(int i=0;i<data7.size();i++) {
                        if (i == 0 || data7.get(i).getGname().equals(data7.get(i - 1).getGname())) {
                            ne.add(new pdata(data7.get(i).getName(),data7.get(i).getImage(),"temp"));
                        } else {
                            data8.add(new rdata(data7.get(i - 1).getGname(),ne));
                            ne = new ArrayList<>();
                            ne.add(new pdata(data7.get(i).getName(),data7.get(i).getImage(),"temp"));
                        }
                        if (i == data7.size() - 1) {
                            data8.add(new rdata(data7.get(i).getGname(),ne));
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(new radapter(getApplicationContext(),data8));
                    //Toast.makeText(context, String.valueOf(data7.size()), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, String.valueOf(data3.size()), Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(SplitActivity.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<idata>> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(SplitActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                data8=new ArrayList<>();
            }
        });
        dialog.show();
    }
    private void showdialog5(){
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog = new Dialog(SplitActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.d_close3);
        RecyclerView recyclerView=dialog.findViewById(R.id.recycleg);
        done=dialog.findViewById(R.id.dbtn);
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
        Call<ArrayList<adata>> call=service.getadata();
        call.enqueue(new Callback<ArrayList<adata>>() {
            @Override
            public void onResponse(Call<ArrayList<adata>> call, Response<ArrayList<adata>> response) {
                int status=response.code();
                if(response.isSuccessful()){
                    Context context=getBaseContext();
                    data9=response.body();
                    ArrayList<pdata> ne1=new ArrayList<>();
                    ArrayList<odata> ne2=new ArrayList<>();//spname,spperson,amount split
                    for(int i=0;i<data9.size();i++) {
                        if (i == 0 || data9.get(i).getGname().equals(data9.get(i - 1).getGname())) {
                            ne1.add(new pdata(data9.get(i).getName(), data9.get(i).getImage(), "temp"));
                            ne2.add(new odata(data9.get(i).getSpname(),data9.get(i).getSpperson(),data9.get(i).getAmount()));
                        } else {
                            data10.add(new ldata(data9.get(i - 1).getGname(),ne1,ne2));
                            ne1= new ArrayList<>();
                            ne2=new ArrayList<>();
                            ne1.add(new pdata(data7.get(i).getName(), data7.get(i).getImage(), "temp"));
                            ne2.add(new odata(data9.get(i).getSpname(),data9.get(i).getSpperson(),data9.get(i).getAmount()));
                        }
                        if (i == data7.size() - 1) {
                            data10.add(new ldata(data9.get(i).getGname(),ne1,ne2));
                        }
                    }
                }
                else{
                    Toast.makeText(SplitActivity.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
                }
                }

            @Override
            public void onFailure(Call<ArrayList<adata>> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(SplitActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}