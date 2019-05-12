package com.example.cy.dake;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.listener.FindListener;


public class MainActivity extends AppCompatActivity{
 private EditText loginet1=null,loginet2=null;
 private Button login=null;
 private TextView zhuce=null;
 private String name=null,password,bmobpassword;
 private boolean querenname=false,querenpassword=false;



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        loginet1=(EditText)findViewById( R.id.loginet1 );
        loginet2=(EditText)findViewById( R.id.loginet2 );
        login=findViewById( R.id.login );
        zhuce=(TextView)findViewById( R.id.login2 );

       TextView.OnClickListener l=new TextView.OnClickListener(){

            @Override
            public void onClick( View view ) {
                if (view.getId()==R.id.login2){
                    Intent intent=new Intent();
                    intent.setClass( MainActivity.this,zhuce.class );
                    startActivity(intent);
                }
            }
        };
       zhuce.setOnClickListener(l);


        Button.OnClickListener listener=new  Button.OnClickListener(){
            @Override
            public void onClick( View view ) {

                if(view.getId()==R.id.login){
                    name=loginet1.getText().toString();
                    password=loginet2.getText().toString();
                    cn.bmob.v3.BmobQuery<Person> bmobQuery = new cn.bmob.v3.BmobQuery<Person>();
                    bmobQuery.addWhereEqualTo( "name",name );
                    bmobQuery.findObjects( MainActivity.this, new FindListener <Person>() {
                        @Override
                        public void onSuccess( List<Person> list ) {
                                Person p=list.get( 0 );
                                bmobpassword=p.getpassword();
                                if(list.size()!=0&&password.equals(bmobpassword  )){
                                    ((MyApplication)getApplication()).setCheckname( name );
                                    Intent intent=new Intent();
                                    intent.setClass( MainActivity.this,login.class );
                                    startActivity(intent);
                                }else {

                                    Toast.makeText(MainActivity.this, "密码错误!", Toast.LENGTH_SHORT).show();
                                }

                        }

                        @Override
                        public void onError( int i, String s ) {

                        }
                    } );

                }
            }
        };
       login.setOnClickListener( listener );



    }
}
