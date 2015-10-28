package com.example.colleenminor.theadventure.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;
import com.example.colleenminor.theadventure.models.User;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private TextView mTakeItem;
    private TextView mNextRoom;
    private int mChoice;
    private SharedPreferences mPrefrefences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTakeItem = (TextView) findViewById(R.id.takeItem);
        mNextRoom = (TextView) findViewById(R.id.nextRoom);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);

        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemsListActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);


        if (!nameChosen()) {
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        }

        mTakeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item("candlestick", mUser);
                item.save();
            }
        });
    }





    private boolean nameChosen() {
        String username = mPreferences.getString("username", null);
        if (username == null) {
            return false;
        } else {
            setUser(username);
            return true;
        }
    }

    private void setUser(String username) {
        User user = User.find(username);
        if (user != null) {
            mUser = user;
        } else {
            mUser = new User(username);
            mUser.save();
        }
        Toast.makeText(this, "Welcome " + mUser.getName(), Toast.LENGTH_LONG).show();

    }
}
