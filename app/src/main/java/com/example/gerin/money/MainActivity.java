package com.example.gerin.money;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// install the OkHttp library using Maven or Gradle
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//
//class YourApp {
//    public static void main(String[] args) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://api.td-davinci.com/api/accounts/self")
//                .addHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMTQwZjQ4YWUtMmQ5YS0zMjQxLTkyYzktOWMzMDc3YWY2ZGU3IiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJhcHBfaWQiOiIzYTg5NjFlMy05YTA4LTQ5ZTktYmQwNS1lNGJkNDMyNTg5NDMifQ.MHqNE8aw6pKcw9uV_xRVDBwozzfeNiVlExMa3gXGyRs")
//                .build();
//
//        Response response = client.newCall(request).execute();
//        String result = response.body().string();
//        System.out.println(result);
//    }
//}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //create second thread to update progress bar
//        Thread second = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try{
//                    Message msg = new Message();
//
//                    function();
//
//                    msg.what = 1;
//                    handler.sendMessage(msg);
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){} catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        second.start();

    }

    public static void function() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.td-davinci.com/api/branches")
                .addHeader("Authorization", "YOUR API KEY GOES HERE")
                .build();

        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg){
//            int currentPosition = msg.what;
//
//            //update play button
//            if(!mp.isPlaying()) {
//                playButton.setBackgroundResource(R.drawable.play_button);
//            }
//
//            //continuously update progress bar
//            progressBar.setProgress(currentPosition);
//
//            //continuously update time stamps
//            String elapsedTime = createTimeStamp(currentPosition);
//            elapsedTimeLabel.setText(elapsedTime);
//            String remainingTime = createTimeStamp(totalTime - currentPosition);
//            remainingTimeLabel.setText(remainingTime);

        }

    };


}
