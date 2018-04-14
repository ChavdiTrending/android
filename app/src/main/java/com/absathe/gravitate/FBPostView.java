package com.absathe.gravitate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FBPostView extends AppCompatActivity {

    private String webViewURL = null;
    private WebView webView = null;
    private String js = "javascript:" +
            "document.getElementById(\"mobile_login_bar\").style.display=\"none\";\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbpost_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webViewURL = getIntent().getStringExtra("webViewURL");
        if(webViewURL == null) {
            Toast.makeText(FBPostView.this,"Something went wrong with that request", Toast.LENGTH_LONG).show();
            return;
        }
        webView = findViewById(R.id.fbpost_view_webview);
        renderWebView();
    }
    private void renderWebView() {
        Toast.makeText(FBPostView.this, "Initializing", Toast.LENGTH_LONG).show();
        WebSettings webSettings = webView.getSettings();
        webSettings.setAppCacheEnabled(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                view.evaluateJavascript(js, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {

                    }
                });
                Toast.makeText(FBPostView.this, "I'm done loading the JS", Toast.LENGTH_LONG).show();
            }
        });
        webView.loadUrl(webViewURL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fbpost_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
