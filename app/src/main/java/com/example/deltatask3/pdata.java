package com.example.deltatask3;

public class pdata {
    String pname;
    String pimage;
    String add;

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

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public pdata(String pname, String pimage, String add) {
        this.pname = pname;
        this.pimage = pimage;
        this.add=add;
    }
}
