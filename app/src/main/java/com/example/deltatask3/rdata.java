package com.example.deltatask3;

import java.util.ArrayList;

public class rdata {
    String gname;
    ArrayList<pdata> rarray;

    public rdata(String gname, ArrayList<pdata> rarray) {
        this.gname = gname;
        this.rarray = rarray;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public ArrayList<pdata> getRarray() {
        return rarray;
    }

    public void setRarray(ArrayList<pdata> rarray) {
        this.rarray = rarray;
    }
}
