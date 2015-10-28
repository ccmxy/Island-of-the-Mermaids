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

public class LagoonActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private TextView mTakeShells;
    private TextView mActionsRemaining;
    private int mActions = 2;
    private TextView mRunIntoRoom;
    private int mChoice;
    private SharedPreferences mPrefrefences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTakeShells = (TextView) findViewById(R.id.pickUpShells);
        mRunIntoRoom = (TextView) findViewById(R.id.runIntoLagoon);
        mActionsRemaining = (TextView) findViewById(R.id.actionsRemaining);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LagoonActivity.this, ItemsListActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);

        mTakeShells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = new Item("seashells", mUser);
                item.save();
                Toast.makeText(LagoonActivity.this, mUser.getName() + ", seashells have been added to your inventory", Toast.LENGTH_LONG).show();
                mActions--;
                mActionsRemaining.setText("Actions remaining: " + String.valueOf(mActions));
                mTakeShells.setVisibility(View.GONE);
            }
        });

        mRunIntoRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LagoonActivity.this, MermaidActivity.class);
                startActivity(intent);
            }

        });

    }

}
