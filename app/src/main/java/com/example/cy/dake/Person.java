package com.example.cy.dake;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Person extends BmobObject {
     private String password;
    private String name;
    private BmobFile icon;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getpassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public BmobFile getIcon(){
        return icon;
    }
    public void setIcon(BmobFile icon){
        this.icon = icon;
    }

}
