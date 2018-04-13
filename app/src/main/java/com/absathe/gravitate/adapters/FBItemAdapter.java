package com.absathe.gravitate.adapters;

import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.absathe.gravitate.FBFragment;
import com.absathe.gravitate.R;
import com.absathe.gravitate.items.FBItem;

import java.util.List;

/**
 * Created by ABSathe on 13-04-2018.
 */

public class FBItemAdapter extends RecyclerView.Adapter<FBItemAdapter.ViewHolder> {
    private List<FBItem> fbItemList = null;
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
    private String js = "javascript:" +
            "var url = null;" +
            "url = document.images[document.images.length - 3].src;" +
            "if(url) {" +
            "   document.location.assign(url); " +
            "}" ;
    public void setItemList(List<FBItem> itemList) {
        this.fbItemList = itemList;
    }
    @Override
    public FBItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fbitem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FBItemAdapter.ViewHolder holder, int position) {
        final FBItem item = fbItemList.get(position);
        final WebView webView = holder.webView;
        final ImageView loading = holder.imageView;
        Button view = holder.button;

        // Set up the webView
        WebSettings webSettings = webView.getSettings();
        boolean isVideo = item.getWebViewURL().contains("video");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            webSettings.setAppCacheEnabled(true);
            webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setAllowFileAccess(false);
            webSettings.setUseWideViewPort(true);
            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setUserAgentString(userAgent);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                Handler timer = new Handler();
                view.evaluateJavascript(js, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {

                    }
                });
                timer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    }
                }, 2500);

            }
        });
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalFadingEdgeEnabled(false);
        if(isVideo == false)
            webView.loadUrl(item.getWebViewURL());
        else
            webView.loadUrl("URL/to/the/Photo");

        // Attach onClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FBFragment.openFacebookPost(item.getWebViewURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fbItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private WebView webView;
        private ImageView imageView;
        private Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.fbitem_webview);
            imageView = itemView.findViewById(R.id.fbitem_loading);
            button = itemView.findViewById(R.id.fbitem_view_button);
        }
    }
}
