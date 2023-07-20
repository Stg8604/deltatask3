package com.example.deltatask3;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String image;
    private String username;
    private int lent;
    private int debt;

    public int getLent() {
        return lent;
    }

    public void setLent(int lent) {
        this.lent = lent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }
}
