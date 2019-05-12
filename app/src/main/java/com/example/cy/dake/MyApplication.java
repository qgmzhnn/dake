package com.example.cy.dake;

import android.app.Application;

public class MyApplication extends Application {
    public String checkname;
    @Override

    public void onCreate() {

        // TODO Auto-generated method stub

        super.onCreate();

    }
    public String getCheckname() {
        return checkname;
    }
    public void setCheckname(String checkname) {
        this.checkname = checkname;
    }
}
