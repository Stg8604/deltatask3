package com.example.deltatask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private EditText edt1,edt2,edt3,edt4,edt5,edt6;
    private Button btnsign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edt1=findViewById(R.id.user);
        edt2=findViewById(R.id.im);
        edt3=findViewById(R.id.lent);
        edt4=findViewById(R.id.debt);
        edt5=findViewById(R.id.pp);
        edt6=findViewById(R.id.confpp);
        btnsign=findViewById(R.id.signal);
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt5.getText().toString().equals(edt6.getText().toString())) {
                    signing();
                }else{
                    Toast.makeText(SignUpActivity.this, "Passwords dont match,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void signing() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Data> call = service.signup(edt1.getText().toString(),edt2.getText().toString(),Integer.parseInt(edt3.getText().toString()),Integer.parseInt(edt4.getText().toString()),edt5.getText().toString());
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                int state=response.code();
                if (response.isSuccessful()) {
                    //Toast.makeText(SignUpActivity.this, "success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(SignUpActivity.this,String.valueOf(state), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("API","Error");
                System.out.println(t.getMessage());
                Toast.makeText(SignUpActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
        startActivity(intent);
    }
}