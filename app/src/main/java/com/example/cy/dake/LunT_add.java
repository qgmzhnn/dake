package com.example.cy.dake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class LunT_add extends AppCompatActivity {
private Button add;
private EditText lunt_et;
private String name;
private  BmobFile bmobFile;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lun_t_add );
        add=findViewById( R.id.lunt_fb );
        lunt_et=(EditText)findViewById( R.id.lunt_et);


        Button.OnClickListener listener=new Button.OnClickListener(){

            @Override
            public void onClick( View view ) {
                if (view.getId()==R.id.lunt_fb){
                    name=((MyApplication)getApplication()).getCheckname();
                    String neirong=lunt_et.getText().toString();
                    BmobQuery< Person > bmobQuery=new BmobQuery <  Person >(  );
                    bmobQuery.addWhereEqualTo("name",name );

                        bmobQuery.findObjects( LunT_add.this, new FindListener <Person>() {
                            @Override
                            public void onSuccess( List <Person> list ) {
                                int n=list.size();
                                //Toast.makeText(LunT_add.this, "yes!", Toast.LENGTH_SHORT).show();

                                Person person=list.get( 0);
                                bmobFile=person.getIcon();

                            }
                            @Override
                            public void onError( int i, String s ) {
                                Toast.makeText(LunT_add.this, "no!", Toast.LENGTH_SHORT).show();
                            }
                        } );
                    final lunt_neirong lunt_neirong=new lunt_neirong();
                    lunt_neirong.setName(name );
                    lunt_neirong.setNeirong( neirong );
                    lunt_neirong.save( LunT_add.this, new SaveListener() {
                        @Override
                        public void onSuccess() {


//                            Toast.makeText(LunT_add.this, "发表成功!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure( int i, String s ) {
                            Toast.makeText(LunT_add.this, "发表失败!", Toast.LENGTH_SHORT).show();
                        }
                    } );
                            BmobQuery< lunt_neirong > bmobQuery1=new BmobQuery < lunt_neirong >(  );
                            bmobQuery1.addWhereEqualTo( "name",name );
                            bmobQuery1.findObjects( LunT_add.this, new FindListener < lunt_neirong >() {
                                @Override
                                public void onSuccess( List< lunt_neirong > list ) {
                                    int n=list.size();
                                    for(int i=0;i<n;i++){
                                        lunt_neirong lunt_neirong1=list.get(i );
                                        //lunt_neirong1.setName( name );
                                        lunt_neirong1.setIcon( bmobFile );
                                        lunt_neirong1.update( LunT_add.this, new UpdateListener() {
                                            @Override
                                            public void onSuccess() {
                                                Toast.makeText(LunT_add.this, "发表成功!", Toast.LENGTH_SHORT).show();
//                                                Intent  intent=new Intent(  );
//                                                intent.setClass( LunT_add.this,LunT.class );
//                                                startActivity( intent );
                                            }

                                            @Override
                                            public void onFailure( int i, String s ) {
                                                Toast.makeText(LunT_add.this, "修改失败!", Toast.LENGTH_SHORT).show();
                                            }
                                        } );

                                    }


                                }

                                @Override
                                public void onError( int i, String s ) {
                                    Toast.makeText(LunT_add.this, "失败!", Toast.LENGTH_SHORT).show();
                                }
                            } );
                }
            }
        };
       add.setOnClickListener( listener );

    }
}
