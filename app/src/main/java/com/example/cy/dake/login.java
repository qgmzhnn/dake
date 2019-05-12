package com.example.cy.dake;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.Bmob;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class login extends AppCompatActivity implements View.OnClickListener{
    private RadioGroup radioGroup;
    private RadioButton button_1;
    private RadioButton button_2;
    private RadioButton button_3;
    private RadioButton button_4;
    private List<FrameLayout> list;
    private FrameLayout frameLayout;
    private  FrameLayout_1 frameLayout_1;
    private FrameLayout_wode frameLayout_wode;
    private ImageButton ibkc;

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        //找到四个按钮
        button_1 = (RadioButton) findViewById(R.id.button_1);
        button_2 = (RadioButton) findViewById(R.id.button_2);
        button_3 = (RadioButton) findViewById(R.id.button_3);
        button_4 = (RadioButton) findViewById(R.id.button_4);

        //创建Fragment对象及集合
        frameLayout_1 = new FrameLayout_1();
        frameLayout_wode=new FrameLayout_wode();


        //将Fragment对象添加到list中
        ArrayList <Object> list = new ArrayList <>();
        list.add(frameLayout_1);
        list.add( frameLayout_wode );

        //设置RadioGroup开始时设置的按钮，设置第一个按钮为默认值
        radioGroup.check(R.id.button_1);


        //设置按钮点击监听
        button_1.setOnClickListener( (View.OnClickListener) this );
        button_4.setOnClickListener( (View.OnClickListener) this );

        //初始时向容器中添加第一个Fragment对象
        addFragment(frameLayout_1);
    }

    @Override
    public void finish() {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.removeAllViews();
        super.finish();
    }

    //点击事件处理
    public void onClick(View v) {
        //我们根据参数的id区别不同按钮
        //不同按钮对应着不同的Fragment对象页面
        switch (v.getId()) {
            case R.id.button_1:
                addFragment(frameLayout_1);
                break;
            case R.id.button_4:
                addFragment(frameLayout_wode);
                break;
            default:
                break;
        }

    }

    //向Activity中添加Fragment的方法
    public void addFragment(Fragment fragment) {

        //获得Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //使用管理器开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //使用事务替换Fragment容器中Fragment对象
        fragmentTransaction.replace(R.id.framelayout,fragment);
        //提交事务，否则事务不生效
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        initView();



    }
}
