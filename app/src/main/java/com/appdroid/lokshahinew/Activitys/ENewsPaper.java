package com.appdroid.lokshahinew.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.appdroid.lokshahinew.R;

public class ENewsPaper extends AppCompatActivity {

    private WebView enews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enews_paper);

        enews = findViewById(R.id.enews);

        enews.setVisibility(View.GONE);

        WebSettings webSettings=enews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        enews.loadUrl("http://epaper.lokshahilive.com/#Page/1");


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enews.setVisibility(View.VISIBLE);
            }
        },1000);
    }
}