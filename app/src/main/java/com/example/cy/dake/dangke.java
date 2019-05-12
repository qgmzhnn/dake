package com.example.cy.dake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dangke extends AppCompatActivity {
private TextView tvsc;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dangke2 );
        tvsc=findViewById( R.id.tvSC );
        TextView.OnClickListener listener=new  TextView.OnClickListener(){

            @Override
            public void onClick( View view ) {
                if(view.getId()==R.id.tvSC){
                    Intent intent=new Intent();
                    intent.setClass(dangke.this, rdsc.class);
                    startActivity(intent);
                }
            }
        };
        tvsc.setOnClickListener( listener );
    }
}
