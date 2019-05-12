package com.example.cy.dake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class zhuce extends AppCompatActivity {
    private EditText zhuceet1=null,zhuceet2=null;
    private Button zhuce=null;
    private String name,password;
    public Person p2;
    private boolean queren=true;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_zhuce );
        zhuceet1=(EditText)findViewById(R.id.zhuceet1);
        zhuceet2=(EditText)findViewById(R.id.zhuceet2);
        zhuce=findViewById( R.id.zhuce );
        cn.bmob.v3.Bmob.initialize(this, "837c2e1408315ef88f3ad7bacaabc91a");
        Button.OnClickListener listener=new  Button.OnClickListener(){
            @Override
            public void onClick( View view ) {
                if(view.getId()==R.id.zhuce){
                    name=zhuceet1.getText().toString();
                    password=zhuceet2.getText().toString();
                    cn.bmob.v3.BmobQuery<Person> bmobQuery = new cn.bmob.v3.BmobQuery<Person>();
                    bmobQuery.addWhereEqualTo( "name",name );
                    bmobQuery.findObjects( zhuce.this, new cn.bmob.v3.listener.FindListener<Person>() {
                        @Override
                        public void onSuccess( List<Person> list ) {
                            if (list.size()>0){
                                queren=false;
                            }
                        }
                        @Override
                        public void onError( int i, String s ) {

                        }
                    } );
                    if (queren){
                        p2 = new Person();
                        p2.setName(name);
                        p2.setPassword(password);
                        p2.save( zhuce.this, new cn.bmob.v3.listener.SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(zhuce.this, "submit success!", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure( int i, String s ) {
                                Toast.makeText(zhuce.this, "submit failure!", Toast.LENGTH_SHORT).show();
                            }
                        } );

                        Intent intent=new Intent();
                        intent.setClass( zhuce.this,MainActivity.class );
                        startActivity(intent);

                    }else{

                        Toast.makeText( zhuce.this,"用户名已存在",Toast.LENGTH_SHORT ).show();
                        queren=true;
                    }


                }
            }
        };
        zhuce.setOnClickListener( listener );
    }
}
