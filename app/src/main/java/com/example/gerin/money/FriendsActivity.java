package com.example.gerin.money;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FriendsActivity extends AppCompatActivity {

    String[] mobileArray = {"Sam (Me)","Jim","Bob"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list_item, mobileArray);

        ListView listView = (ListView) findViewById(R.id.my_listview);
        listView.setAdapter(adapter);


    }
}
