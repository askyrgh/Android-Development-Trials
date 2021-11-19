package com.example.javawebgltestbench;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewLauncherActivity extends AppCompatActivity {

    private static final String URL_MESSAGE = "";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_launcher);

        getSupportActionBar().hide();

        webView = findViewById(R.id.webView);
        String requestedURL = getIntent().getStringExtra(URL_MESSAGE);

        //webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.setWebChromeClient(new WebChromeClient(){
            // Need to accept permissions to use the camera
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Log.d("requestPermission","onPermissionRequest");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.getResources());
                }
            }
        });

//        webView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onPermissionRequest(PermissionRequest request) {
//                runOnUiThread(() -> {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        String[] PERMISSIONS = {
//                                PermissionRequest.RESOURCE_AUDIO_CAPTURE,
//                                PermissionRequest.RESOURCE_VIDEO_CAPTURE
//                        };
//                        request.grant(PERMISSIONS);
//                    }
//                });
//            }
//        });

        webView.loadUrl(requestedURL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}