package com.example.cy.dake;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class lunt_neirong extends BmobObject{
    private String neirong;
    private String name;
    private BmobFile icon;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNeirong(){
        return  neirong;
    }
    public void setNeirong(String neirong){
        this.neirong=neirong;
    }
    public BmobFile getIcon(){
        return icon;
    }
    public void setIcon(BmobFile icon){
        this.icon = icon;
    }
}
