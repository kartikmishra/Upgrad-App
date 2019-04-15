package com.example.upgradapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.upgradapp.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private String token_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button button = findViewById(R.id.loginButton);
        final WebView webView = findViewById(R.id.webView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button.setVisibility(View.INVISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://stackoverflow.com/oauth/dialog?client_id=14913&scope_private_info&redirect_uri=https://stackexchange.com/oauth/login_success");
            }
        });


    }



}
