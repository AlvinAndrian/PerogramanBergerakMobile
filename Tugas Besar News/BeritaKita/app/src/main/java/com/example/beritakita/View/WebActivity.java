package com.example.beritakita.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.beritakita.R;

public class WebActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private WebView mWebView;

    String url = "https://www.google.com";

    @SuppressLint("SetJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getSupportActionBar().hide();

        //android toolbar
//        setupToolbar();

        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setMax(100);
        url =  getIntent().getStringExtra("url");

        mWebView = findViewById(R.id.read_news);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mProgressBar.setProgress(0);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
                view.loadUrl(newUrl);
                mProgressBar.setProgress(0);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
                //mProgressBar.setProgress(0);
                url = urlStart;
                invalidateOptionsMenu();

            }

            @Override
            public void onPageFinished(WebView view, String urlPage) {
                mProgressBar.setVisibility(View.GONE);
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_news, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    private void setupToolbar() {
//        Toolbar toolbar = findViewById(R.id.tbDetailNews);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mWebView.canGoBack()) {
//                    mWebView.goBack();
//                } else {
//                    finish();
//                }
//            }
//        });
//    }
}