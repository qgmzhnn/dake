package com.example.cy.dake;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;

public class FrameLayout_wode extends Fragment {

    private View mView;
    private ImageView wd_iv;
    private TextView wd_tv;
    private BmobFile bmobFile;
    private String path,filename;
    public Bitmap getPicture( String path){
        Bitmap bm=null;
        try{
            URL url=new URL(path);
            URLConnection connection=url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            bm= BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //bm = BitmapZoom.resizeImage(bm , 300 , 300);//修改图片大小
        return  bm;
    }
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 16) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //注意View对象的重复使用，以便节省资源
        if(mView == null) {
            mView = inflater.inflate(R.layout.activity_wode2,container,false);
        }
        wd_iv=mView.findViewById( R.id.wd_iv );
        wd_tv=mView.findViewById( R.id.wd_tv );
        wd_tv.setText(" "+((MyApplication)getActivity().getApplication()).getCheckname());
        ImageView.OnClickListener listener=new ImageView.OnClickListener(){
            @Override
            public void onClick( View view ) {
                if (view.getId()==R.id.wd_iv){
                    Intent intent=new Intent();
                    intent.setClass(getActivity(),Add.class );
                    startActivity( intent );
                }
            }
        };
        wd_iv.setOnClickListener( listener );
        String name=((MyApplication)getActivity().getApplication()).getCheckname();
        BmobQuery<Person> bmobQuery=new BmobQuery <Person>(  );
        bmobQuery.addWhereEqualTo( "name",name );
        bmobQuery.findObjects( getActivity(), new FindListener <Person>() {
            @Override
            public void onSuccess( List<Person> list ) {
                Person person=list.get( 0 );
                bmobFile=person.getIcon();

                   filename=bmobFile.getFilename();
                   path=bmobFile.getUrl();
                   wd_iv.setImageBitmap( getPicture( path ) );
            }

            @Override
            public void onError( int i, String s ) {
            }
        } );

        return mView;
    }
}