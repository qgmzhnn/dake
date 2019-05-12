package com.example.cy.dake;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;

public class LunT extends AppCompatActivity {
    ImageView imageView ;
    ListView listView;
    private BmobFile bmobFile;
    private  URL url;
    private  int n=0;
    private  BmobFile image;
    private  TextView tv3,tv7;
    private String s;
    private  int i=0;
    private Button fb;
    class ViewHolder{
        ImageView iv_img;
        TextView lunt_name;
        TextView lunt_neirong,lunt_time;

    }
    private void loadDate() {
        //final Goods[] goods ; //= (List<Goods>) new Goods();
        final ArrayList<lunt_neirong> lunt_neirongs = null;
        //BmobFile image = new BmobFile();
        BmobQuery<lunt_neirong> bmobQuery = new BmobQuery<lunt_neirong>();
        bmobQuery.findObjects(LunT.this, new FindListener<lunt_neirong>() {
           ViewHolder viewHolder;
            @Override
            public void onSuccess( final List<lunt_neirong> list) {

                final String[] name  =  new String[list.size()];
                final String[] neirong  =  new String[list.size()];
                final String[] time  =  new String[list.size()];
                //final String[] s=new String[list.size()];
                //final Bitmap[] bitmap = new Bitmap[list.size()];
                //final String[] uri = new String[list.size()];
                for(int i = 0;i<list.size();i++){
                    name[i] = list.get(i).getName();
                    neirong[i] = list.get(i).getNeirong();
                    time[i] = list.get(i).getCreatedAt();
                    //image= list.get( i ).getIcon();
                    //list.get(i).getIcon();//loadImageThumbnail(GroundActivity.this, imageView, 300, 300);

                }
/**
 *
 */
                class MyAdapter extends BaseAdapter {
                    private Context context ;
                    public MyAdapter(Context context){
                        this.context = context;
                    }

                    @Override
                    public int getCount() {
                        return name.length;
                    }

                    @Override
                    public Object getItem(int position) {
                        return name[position];
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                       ViewHolder viewHolder;
                        if (convertView == null){
                            LayoutInflater inflater = LayoutInflater.from(context);
                            convertView = inflater.inflate(R.layout.item_lunt_layout, null);//实例化一个布局文件
                            viewHolder = new ViewHolder();
                            viewHolder.iv_img = (ImageView)convertView.findViewById(R.id.iv);
                            viewHolder.lunt_name = (TextView)convertView.findViewById(R.id.lunt_name);
                            viewHolder.lunt_neirong = (TextView)convertView.findViewById(R.id.lunt_neirong);
                            viewHolder.lunt_time = (TextView)convertView.findViewById(R.id.lunt_time);

                            convertView.setTag(viewHolder);
                        }else {
                            viewHolder = (ViewHolder) convertView.getTag();
                        }




                        image=list.get( i).getIcon();
                        s=image.getUrl();
                        viewHolder.iv_img.setImageBitmap( getPicture( s ) );
                        //viewHolder.iv_img.setImageIcon( image[po] );
                        // viewHolder.iv_img.setImageBitmap( getPicture( s[0]) );
                        viewHolder.lunt_name.setText("  "+name[position]);
                        viewHolder.lunt_neirong.setText(" "+neirong[position]);
                        viewHolder.lunt_time.setText(" "+time[position]);

                        i++;
                        return convertView;
                    }



                }
                /**
                 *
                 */
                listView.setAdapter(new MyAdapter(LunT.this));
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
    public Bitmap getPicture(String path){
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
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lun_t );
        imageView = (ImageView) findViewById(R.id.iv);
        listView = (ListView) findViewById(R.id.listView);
        fb=findViewById( R.id.lunt_add );
        if (android.os.Build.VERSION.SDK_INT > 16) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
       loadDate();
        Button.OnClickListener listener=new Button.OnClickListener(){

            @Override
            public void onClick( View view ) {
                if (view.getId()==R.id.lunt_add);{
                    Intent intent=new Intent(  );
                    intent.setClass(LunT.this,LunT_add.class );
                    startActivity( intent );
                }
            }
        };
        fb.setOnClickListener( listener );
    }
}
