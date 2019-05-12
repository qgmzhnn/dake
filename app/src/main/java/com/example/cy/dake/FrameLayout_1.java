package com.example.cy.dake;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class FrameLayout_1 extends Fragment {
private ImageButton ibkc;
    private View mView;
    private ImageButton ibdd,ibcs,iblt;
    private List<News> newsList;

    private NewsAdapter adapter;
    private ProgressDialog progressBar;
    private Handler handler;

    private ListView lv;
    private void setListener(){
        ImageButton.OnClickListener listener=new  ImageButton.OnClickListener(){
            @Override
            public void onClick( View view ) {
                if(view.getId()==R.id.ibkc){
                    Intent intent=new Intent();
                    intent.setClass(getActivity(), kecheng.class);
                    startActivity(intent);
                }
            }
        };
        ibkc.setOnClickListener( listener );

        ImageButton.OnClickListener listener1=new  ImageButton.OnClickListener(){
            @Override
            public void onClick( View view ) {
                if(view.getId()==R.id.ibdd){
//                    Intent intent=new Intent();
//                    intent.setClass(getActivity(), historydd.class);
//                    startActivity(intent);
                }
            }
        };
        ibdd.setOnClickListener( listener1 );

        ImageButton.OnClickListener listener3=new  ImageButton.OnClickListener(){
            @Override
            public void onClick( View view ) {
                if(view.getId()==R.id.iblt){
                    Intent intent=new Intent();
                    intent.setClass(getActivity(), LunT.class);
                    startActivity(intent);
                }
            }
        };
        iblt.setOnClickListener( listener3 );
        ibcs.setOnClickListener( listener );
        ImageButton.OnClickListener listener2=new  ImageButton.OnClickListener(){
            @Override
            public void onClick( View view ) {
                if(view.getId()==R.id.ibcs){
//                    Intent intent=new Intent();
//                    intent.setClass(getActivity(), Add.class);
//                    startActivity(intent);
                }
            }
        };
        ibcs.setOnClickListener( listener2 );
    }
    private void findview(){
        ibkc= mView.findViewById( R.id.ibkc );
        iblt= mView.findViewById( R.id.iblt );
        ibdd= mView.findViewById( R.id.ibdd );
        ibcs= mView.findViewById( R.id.ibcs );
       lv=mView.findViewById( R.id.sy_lv );
    }
    private void getNews(){



        new Thread(new Runnable() {

            @Override

            public void run() {

                try{

                    //获取虎扑新闻20页的数据，网址格式为：https://voice.hupu.com/nba/第几页

                    for(int i = 1;i<=5;i++) {


  Document doc = Jsoup.connect("https://china.chinadaily.com.cn/5bd5639ca3101a87ca8ff636/").get();

                    Elements titleLinks = doc.select("div.mr10");    //解析来获取每条新闻的标题与链接地址

                     Elements title2Links = doc.select("div.busBox3");//解析来获取每条新闻的简介

                    //Elements timeLinks = doc.select("div.otherInfo");   //解析来获取每条新闻的时间与来源
                    //textView.setText( Integer.toString( timeLinks.size() ) );
                       // textView.setText( Integer.toString( titleLinks.size() ) );
                    Log.e("title",Integer.toString(titleLinks.size()));

                    for(int j = 0;j < titleLinks.size();j++){

                       String title = title2Links.get(j).select("a").text();

                        String uri = titleLinks.get(j).select("a").attr("href");

                         String desc = title2Links.get(j).select("p").text();

                        //String time = timeLinks.get(j).select("span.other-left").select("a").text();
                    //textView.setText( title);
                        News news = new News(title,uri,desc,null);

                        newsList.add(news);

                        }

                    }

                    Message msg = new Message();

                    msg.what = 1;

                    handler.sendMessage(msg);



                }catch (Exception e){

                    e.printStackTrace();

                }

            }

        }).start();

    }
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //注意View对象的重复使用，以便节省资源
        if(mView == null) {
            mView = inflater.inflate(R.layout.activity_shouye,container,false);
        }

        findview();
        setListener();
        newsList = new ArrayList<>();
        getNews();
        progressBar=  new ProgressDialog(getActivity());
        progressBar.setProgress(1);
      progressBar.setMessage( "正在加载..." );
        progressBar.setIndeterminate(false);

        progressBar.show();


        handler = new Handler(){

            @Override

            public void handleMessage(Message msg) {

                if(msg.what == 1){

                    adapter = new NewsAdapter(getActivity(),newsList);

                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override

                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            News news = newsList.get(position);

                            Intent intent = new Intent(getActivity(),NewsDisplayActvivity.class);

                            intent.putExtra("news_url",news.getNewsUrl());

                            startActivity(intent);

                        }

                    });

                }

            }

        };


        return mView;
    }
}