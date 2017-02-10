package com.cookiejar.serializablecookiejar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.valdio.cookiejar.serializablecookiejar.SerializableCookieJar;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SerializableCookieJar serializableCookieJar = new SerializableCookieJar(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(serializableCookieJar)
                .build();
    }
}
