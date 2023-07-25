package com.example.deltatask3;

public class kdata {
    String spname,spperson,username,image,name;
    int amount;

    public kdata(String spname, String spperson, String username, String image, String name, int amount) {
        this.spname = spname;
        this.spperson = spperson;
        this.username = username;
        this.image = image;
        this.name = name;
        this.amount = amount;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount2() {
        return amount;
    }

    public void setAmount2(int amount) {
        this.amount= amount;
    }
}
