package com.example.cy.dake;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class kecheng extends AppCompatActivity {
private TextView tvkec1;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_kecheng2 );
        tvkec1=findViewById( R.id.tvDK );
        TextView.OnClickListener listener=new  TextView.OnClickListener(){

            @Override
            public void onClick( View view ) {
                if(view.getId()==R.id.tvDK){
                    Intent intent=new Intent();
                    intent.setClass(kecheng.this, dangke.class);
                    startActivity(intent);
                }
            }
        };
       tvkec1.setOnClickListener( listener );
    }
}
