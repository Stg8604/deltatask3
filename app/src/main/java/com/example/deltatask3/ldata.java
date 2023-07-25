package com.example.deltatask3;

import java.util.ArrayList;

public class ldata {
    String lname;
    ArrayList<pdata> lp;
    ArrayList<odata> op;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public ArrayList<pdata> getLp() {
        return lp;
    }

    public void setLp(ArrayList<pdata> lp) {
        this.lp = lp;
    }

    public ArrayList<odata> getOp() {
        return op;
    }

    public void setOp(ArrayList<odata> op) {
        this.op = op;
    }

    public ldata(String lname, ArrayList<pdata> lp, ArrayList<odata> op) {
        this.lname = lname;
        this.lp = lp;
        this.op = op;
    }
}
