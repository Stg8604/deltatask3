package com.example.deltatask3;

import java.util.ArrayList;

public class hdata {
    String spname,spperson;
    ArrayList<New> gdata;

    public hdata(String spname, String spperson, ArrayList<New> gdata) {
        this.spname = spname;
        this.spperson = spperson;
        this.gdata = gdata;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public String getSpperson() {
        return spperson;
    }

    public void setSpperson(String spperson) {
        this.spperson = spperson;
    }

    public ArrayList<New> getGdata() {
        return gdata;
    }

    public void setGdata(ArrayList<New> gdata) {
        this.gdata = gdata;
    }
}
