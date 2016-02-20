package com.example.user.lottery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.user.lottery.R.id.SQLite;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MarinaLotteryApp";
    ArrayList<TextView> arrTextViews = new ArrayList<>();
    LotteryNotification newNotification;
    Button randomNumsButton, sqliteButton;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newNotification = new LotteryNotification(this);

        if(!addLayoutItemsToArrayList()){
            Log.i(TAG, "Failed to add all layout's items to the ArrayList!!!");
        }
        else Log.i(TAG, "Successfully add all layout's items to the ArrayList!!!");
        sqliteButton = (Button)findViewById(SQLite);
        //Event Listener that said to button wait to event when someone will click you.
        sqliteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNotification.buildNotification();
            }
        });
        randomNumsButton = (Button)findViewById(R.id.randomNums);
        //Event Listener that said to button wait to event when someone will click you.
        randomNumsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setRandomLotteryNums(randomNumsButton.getId(),sqliteButton.getId());
                    newNotification.buildNotification();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Log.i(TAG, "onCreate");//fhdb
    }

    public boolean addLayoutItemsToArrayList(){

        rl = (RelativeLayout)findViewById(R.id.rel);
        int item;
        for(item = 0; item < rl.getChildCount(); item++)
        {
            View child = rl.getChildAt(item);
            arrTextViews.add((TextView) child);
        }
        if(item != arrTextViews.size()) return false;
        else return true;
    }
    /*
    Generates random lottery numbers for all textView on the view.
     */
    public void setRandomLotteryNums(int randomNumsButtonID, int sqliteButtonID) {
        Random rand = new Random();
        int index;
        for (TextView textView : arrTextViews) {
            index = rand.nextInt(39);
            for(int i = 0; i<arrTextViews.size(); i++) {
                if (arrTextViews.get(i).getText().toString().equals(String.valueOf(index))) {
                    index = rand.nextInt(39);
                    i = -1;
                }
            }
            if (textView.getId() != randomNumsButtonID || textView.getId() != sqliteButtonID)
                textView.setText(String.valueOf(index));
            Log.i(TAG, textView.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        } catch (InflateException ex) {
            Log.i(TAG, "onCreateOptionsMenu");
            Log.i(TAG, ex.toString());
            ex.printStackTrace();
            return  false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
