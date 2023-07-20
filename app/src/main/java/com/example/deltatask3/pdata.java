package com.example.deltatask3;

public class pdata {
    String pname;
    String pimage;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public pdata(String pname, String pimage) {
        this.pname = pname;
        this.pimage = pimage;
    }
}
