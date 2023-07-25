package com.example.deltatask3;

public class odata {
    String oname,operson;
    float amt;

    public odata(String oname, String operson, float amt) {
        this.oname = oname;
        this.operson = operson;
        this.amt = amt;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOperson() {
        return operson;
    }

    public void setOperson(String operson) {
        this.operson = operson;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }
}
