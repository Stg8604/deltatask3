package com.example.deltatask3;

import android.os.Parcel;
import android.os.Parcelable;

public class tdata {
    String name;
    String image;
    int money;
    String settle;

    public tdata(String name ,int money, String image,String settle) {
        this.name = name;
        this.image = image;
        this.money = money;
        this.settle = settle;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSettle() {
        return settle;
    }

    public void setSettle(String settle) {
        this.settle = settle;
    }
}
