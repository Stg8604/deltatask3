package com.example.deltatask3;

import static com.example.deltatask3.LoginActivity.debt;
import static com.example.deltatask3.LoginActivity.lent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Dashboard extends AppCompatActivity {
    private String name,image;
    private TextView usetxt,owemoney,lentmoney;
    private CardView itransaction,isplit,hsplit;
    private String rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        name=getIntent().getStringExtra("username");
        image=getIntent().getStringExtra("image");
        lent=getIntent().getIntExtra("lent",0);
        debt=getIntent().getIntExtra("debt",0);
        rec=getIntent().getStringExtra("access");
        usetxt=findViewById(R.id.usetext);
        owemoney=findViewById(R.id.owemoney);
        hsplit=findViewById(R.id.hsplit);
        lentmoney=findViewById(R.id.lentmoney);
        usetxt.setText("Hello "+name+"!");
        owemoney.setText(String.valueOf(debt));
        lentmoney.setText(String.valueOf(lent));
        itransaction=findViewById(R.id.itransaction);
        isplit=findViewById(R.id.isplit);
        itransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,Transactions.class);
                intent.putExtra("acc",rec);
                startActivity(intent);
            }
        });
        isplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialog();
            }
        });
        hsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,SplitHistory.class);
                intent.putExtra("acc",rec);
                startActivity(intent);
            }
        });
    }
    private void showdialog() {
        getWindow().setEnterTransition(new Slide());
        final Dialog dialog = new Dialog(Dashboard.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.d_open);
        Button btnsign=dialog.findViewById(R.id.amenter);
        EditText edt=dialog.findViewById(R.id.amedt);
        EditText edt2=dialog.findViewById(R.id.amname);
        EditText edt3=dialog.findViewById(R.id.spln);
        dialog.getWindow().setLayout(1000, 900);
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,SplitActivity.class);
                intent.putExtra("amt",Integer.parseInt(edt.getText().toString()));
                intent.putExtra("name",edt2.getText().toString());
                intent.putExtra("Split",edt3.getText().toString());
                intent.putExtra("acc",rec);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}