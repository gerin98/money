package com.example.gerin.money;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

import com.example.gerin.money.apis.TDDataHandler;
//import com.example.gerin.money.databinding.ActivityMainBinding;
import com.example.gerin.money.objects.AccountObject;
import com.example.gerin.money.objects.CustomerObject;

import com.example.gerin.money.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(Html.fromHtml("<font color='#ffffff'> Smart Budget </font>"));
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_white_18dp);

        mDrawerLayout = findViewById(R.id.drawer);

        TDDataHandler tdAPI = new TDDataHandler(this);
        CustomerObject customer = new CustomerObject();
        tdAPI.getCustomerAccounts("8c71e8d0-e63b-4365-b9d9-f2a30817fa4e_c18dca28-f10f-4a0a-b905-db636046bd4c", customer);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AccountObject account1 = new AccountObject();
        tdAPI.getAccountData("8c71e8d0-e63b-4365-b9d9-f2a30817fa4e_d62ec0ba-6f0a-447f-a3cd-0c09211fd97a", account1);

//        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        mainBinding.setAccount(account1);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        onOptionsItemSelected(menuItem);
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        CircleImageView image = findViewById(R.id.profile_image);
        View header = navigationView.getHeaderView(0);
        TextView namedisplay = (TextView) header.findViewById(R.id.navheader_fullname);

        CircleImageView profilepic = (CircleImageView) header.findViewById(R.id.profile_image);

        mDatabase.child("users").child(user.getUid()).child("gender").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().toString() == "Male") {
                    profilepic.setImageResource(R.drawable.maleavatar);
                } else if (dataSnapshot.getValue().toString() == "Female") {
                    profilepic.setImageResource(R.drawable.femaleavatar);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child("users").child(user.getUid()).child("fullname").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namedisplay.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.friends:
                Intent myIntent = new Intent(MainActivity.this, FriendsActivity.class);
//                myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
                break;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}
