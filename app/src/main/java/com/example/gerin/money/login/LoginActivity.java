package com.example.gerin.money.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gerin.money.MainActivity;
import com.example.gerin.money.R;
import com.example.gerin.money.login.create.CreateAccountActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;

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

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        TextView signup = findViewById(R.id.signup_button);
        Button signin = findViewById(R.id.signin_button);

        EditText email = findViewById(R.id.email_login);
        EditText password = findViewById(R.id.password_login);

        signup.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class)));

    }
}
