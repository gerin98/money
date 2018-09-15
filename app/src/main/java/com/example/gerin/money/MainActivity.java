package com.example.gerin.money;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;

// install the OkHttp library using Maven or Gradle
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#ffffff'> Smart Budget </font>"));
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_white_18dp);

        mDrawerLayout = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
