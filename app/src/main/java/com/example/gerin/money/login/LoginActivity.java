package com.example.gerin.money.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.crashlytics.android.Crashlytics;
import com.example.gerin.money.MainActivity;
import com.example.gerin.money.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getSupportActionBar().hide();

        Button crashButton = findViewById(R.id.signin_button);
        crashButton.setText("Enter");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               Intent nextIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(nextIntent);
                finish();
            }
        });
    }
}
