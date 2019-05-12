package com.example.cy.dake;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class rdsc extends AppCompatActivity  {
private VideoView videoView;

private TextView tv;
private  String path;
private  URL url;
private BmobFile bmobFile;
private Uri uri;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_rdsc2 );

        videoView = (VideoView) findViewById( R.id.videoView );
        tv = (TextView) findViewById( R.id.tv_neidiong );
        //videoView.setVideoPath( "/storage/emulated/0/123456789/rdsc.mp4" );


        final cn.bmob.v3.BmobQuery <Movie> bmobQuery = new cn.bmob.v3.BmobQuery <Movie>();

        bmobQuery.findObjects( rdsc.this, new FindListener <Movie>() {
            @Override
            public void onSuccess( List <Movie> list ) {
                //Toast.makeText( rdsc.this,"查询成功",Toast.LENGTH_SHORT ).show();
                int n = list.size();


                Movie movie = list.get( 0 );

                bmobFile = movie.getMovie();
                String neirong=movie.getjianjie();
                tv.setText("  "+ neirong );
                path = bmobFile.getUrl();
                uri = Uri.parse( path );
                videoView.setVideoURI( uri );
                MediaController mediaController = new MediaController( rdsc.this );
                videoView.setMediaController( mediaController );
                videoView.start();
                videoView.requestFocus();

            }

            @Override
            public void onError( int i, String s ) {
                Toast.makeText( rdsc.this, "查询失败", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
