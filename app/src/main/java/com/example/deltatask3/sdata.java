package com.example.deltatask3;

public class sdata {
    String sname;
    String image;
    float amt;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getAmt() {
        return amt;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public sdata(String sname, String image, float amt) {
        this.sname = sname;
        this.image = image;
        this.amt = amt;
    }
}
