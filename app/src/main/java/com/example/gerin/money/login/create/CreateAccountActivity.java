package com.example.gerin.money.login.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gerin.money.MainActivity;
import com.example.gerin.money.R;
import com.example.gerin.money.objects.UserObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        TAG = "CreateAccountActivity";

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        EditText email = findViewById(R.id.createaccount_emailaddress);
        EditText password = findViewById(R.id.createaccount_password);
        EditText fullname = findViewById(R.id.createaccount_name);
        EditText username = findViewById(R.id.createaccount_username);

        RadioGroup genders = findViewById(R.id.createaccount_gender);

        Button submit = findViewById(R.id.createaccount_signup);

        submit.setOnClickListener(v -> {

            if (email.getText().toString() == "" || password.getText().toString() == "" ||
                    fullname.getText().toString() == "" || username.getText().toString() == "") {
                Toast.makeText(this, "You dumb bitch", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserObject object = new UserObject();
                                    object.setEmail(email.getText().toString());
                                    object.setFullname(fullname.getText().toString());
                                    object.setGender("" + genders.getCheckedRadioButtonId());
                                    object.setUsername(username.getText().toString());

                                    if (genders.getCheckedRadioButtonId() == 1) {
                                        object.setGender("Male");
                                    } else if (genders.getCheckedRadioButtonId() == 2) {
                                        object.setGender("Female");
                                    } else {
                                        object.setGender("Neither");
                                    }
                                    mDatabase.child("users").child(user.getUid()).setValue(object);
                                    startActivity(new Intent(CreateAccountActivity.this, MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
