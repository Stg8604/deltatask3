package com.example.deltatask3;

public class idata {
    String username;
    String gname;
    String image;
    String name;

    public idata(String username, String gname, String image, String name) {
        this.username = username;
        this.gname = gname;
        this.image = image;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        gname = gname;
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
}
