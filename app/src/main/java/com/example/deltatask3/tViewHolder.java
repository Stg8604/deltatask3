package com.example.deltatask3;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class tViewHolder extends RecyclerView.ViewHolder {
    ImageView img1,img2,img3,img4,img5;
    TextView tname,tmoney,sname,smoney,pname,gmoney,gname,hname,htitle,jname,iname,aname,fname,fperson,famount;
    Button tsettle,pbtn,ibtn;
    RecyclerView recycle,recyleg,recylea,recyleb;

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
        img4=itemView.findViewById(R.id.gimg);
        gmoney=itemView.findViewById(R.id.gmoney);
        gname=itemView.findViewById(R.id.gname);
        hname=itemView.findViewById(R.id.hspname);
        htitle=itemView.findViewById(R.id.hsptitle);
        recycle=itemView.findViewById(R.id.recyclehello);
        img5=itemView.findViewById(R.id.jimg);
        jname=itemView.findViewById(R.id.jname);
        iname=itemView.findViewById(R.id.ig);
        recyleg=itemView.findViewById(R.id.rec);
        ibtn=itemView.findViewById(R.id.sel);
        recylea=itemView.findViewById(R.id.recyclea1);
        recyleb=itemView.findViewById(R.id.recyclea2);
        aname=itemView.findViewById(R.id.ggg);
        fname=itemView.findViewById(R.id.fname);
        fperson=itemView.findViewById(R.id.fperson);
        famount=itemView.findViewById(R.id.famount);
    }

}
