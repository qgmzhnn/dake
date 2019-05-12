package com.example.cy.dake;

import android.app.ProgressDialog;
import android.net.http.SslError;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import android.webkit.WebViewClient;



public class NewsDisplayActvivity extends AppCompatActivity {
    private ProgressDialog progressBar;
private  WebView webView;

    private String newsUrl;
private  boolean check=false;
public  boolean Check(){
    webView.loadUrl("https:"+newsUrl);
    return true;
}

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_display_actvivity);

        newsUrl = getIntent().getStringExtra("news_url");

        webView = (WebView) findViewById(R.id.web_view);


        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false

        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞

        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true

        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false

        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式

        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题

        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存

        webView.getSettings().setDomStorageEnabled(true);//DOM Storage

        webView.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用

        webView.setWebViewClient(new WebViewClient(){
            public void onReceivedSslError( WebView view, SslErrorHandler handler, SslError error) {
                handler.cancel();
                // 接受证书
                handler.proceed();

            }
                 });
        progressBar = ProgressDialog.show(this, "", "正在加载...");
        progressBar.setCancelable(true);
        Check();
        if (Check()){
            progressBar.dismiss();

        }




    }

}