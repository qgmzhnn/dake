package com.example.cy.dake;

public class Movie extends cn.bmob.v3.datatype.BmobFile {
    private String name;//电影名称
    private cn.bmob.v3.datatype.BmobFile Movie;//电影文件
    private  String jianjie;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getjianjie() {
        return jianjie;
    }

    public void setjiejian(String name) {
        this.jianjie = jianjie;
    }
    public cn.bmob.v3.datatype.BmobFile getMovie() {
        return Movie;
    }

    public void setMovie( cn.bmob.v3.datatype.BmobFile file) {
        this.Movie = file;
    }
}
