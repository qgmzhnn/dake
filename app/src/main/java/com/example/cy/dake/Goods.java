package com.example.cy.dake;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Goods extends BmobObject {
    private BmobFile icon;
    private String name;
    private String desc;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BmobFile getIcon(){
        return icon;
    }

    public void setIcon(BmobFile icon){
        this.icon = icon;
    }

}
