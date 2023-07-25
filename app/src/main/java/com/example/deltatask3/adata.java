package com.example.deltatask3;

public class adata {
    String username,spname,spperson,gname,name,image;
    float amount;

    public adata(String username, String spname, String spperson, String gname, String name, String image, float amount) {
        this.username = username;
        this.spname = spname;
        this.spperson = spperson;
        this.gname = gname;
        this.name = name;
        this.image = image;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
