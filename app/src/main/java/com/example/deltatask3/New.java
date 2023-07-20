package com.example.deltatask3;

public class New {
    String username,image,newname;
    float amount;

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

    public String getNewname() {
        return newname;
    }

    public void setNewname(String newname) {
        this.newname = newname;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public New(String username, String image, String newname, float amount) {
        this.username = username;
        this.image = image;
        this.newname = newname;
        this.amount = amount;
    }
}
