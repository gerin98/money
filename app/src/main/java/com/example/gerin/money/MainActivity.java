package com.example.gerin.money;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// install the OkHttp library using Maven or Gradle
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class YourApp {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.td-davinci.com/api/branches")
                .addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMTQwZjQ4YWUtMmQ5YS0zMjQxLTkyYzktOWMzMDc3YWY2ZGU3IiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiIzYTg5NjFlMy05YTA4LTQ5ZTktYmQwNS1lNGJkNDMyNTg5NDMifQ.MHqNE8aw6pKcw9uV_xRVDBwozzfeNiVlExMa3gXGyRs")
                .build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
