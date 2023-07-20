package com.example.deltatask3;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class tViewHolder extends RecyclerView.ViewHolder {
    ImageView img1,img2,img3;
    TextView tname,tmoney,sname,smoney,pname;
    Button tsettle,pbtn;

    public tViewHolder(@NonNull View itemView) {
        super(itemView);
        img1= itemView.findViewById(R.id.timg);
        tname= itemView.findViewById(R.id.tname);
        tmoney=itemView.findViewById(R.id.tmoney);
        tsettle=itemView.findViewById(R.id.settler);
        img2=itemView.findViewById(R.id.simg);
        sname=itemView.findViewById(R.id.stxtname);
        smoney=itemView.findViewById(R.id.stxtamt);
        pname=itemView.findViewById(R.id.ptxtname);
        img3=itemView.findViewById(R.id.pimg);
        pbtn=itemView.findViewById(R.id.ptxtadd);
    }

}
