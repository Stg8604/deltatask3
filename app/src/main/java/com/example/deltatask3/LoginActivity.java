package com.example.deltatask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText edt1,edt2;
    private Button loginbtn;
    private String accesstoken;
    private OkHttpClient client = new OkHttpClient();
    private String name="hi";
    private String image;
    public static int lent,debt;
    static String usethisname;
    private ArrayList<tdata> recieve=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt1=findViewById(R.id.name);
        edt2=findViewById(R.id.pass);
        loginbtn=findViewById(R.id.loginbutton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToEndpoint();
            }
        });
    }
    private void sendDataToEndpoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Data> call = service.loginaccess(edt1.getText().toString(),edt2.getText().toString());
        usethisname=edt1.getText().toString();
        //Call<Data> call=service.sendData(user);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                int state=response.code();
                if (response.isSuccessful()) {
                    accesstoken=response.body().getAccessToken();
                    //Toast.makeText(LoginActivity.this, accesstoken, Toast.LENGTH_SHORT).show();
                    getall();


                } else {
                    Toast.makeText(LoginActivity.this,String.valueOf(state), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getdebt(){
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
                .baseUrl("http://localhost:8000/token/users/me/debt/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service=retrofit.create(Service.class);
        Call<Integer> call=service.getDebt();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int status=response.code();
                if(response.isSuccessful()){
                   int debt=response.body();
                   Toast.makeText(LoginActivity.this,String.valueOf(debt), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getall(){
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
        Call<User> call=service.getAll();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int status=response.code();
                if(response.isSuccessful()){
                    name=response.body().getName();
                    image=response.body().getImage();
                    lent=response.body().getLent();
                    debt=response.body().getDebt();
                    Intent intent=new Intent(LoginActivity.this,Dashboard.class);
                    intent.putExtra("username",name);
                    intent.putExtra("image",image);
                    intent.putExtra("lent",lent);
                    intent.putExtra("debt",debt);
                    intent.putExtra("access",accesstoken);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"User Does Not Exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}