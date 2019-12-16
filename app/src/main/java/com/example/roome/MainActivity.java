package com.example.roome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * this activity is presenting the app logo for a few seconds and then disappears.
 * this activity will be shown on every app visit.
 */
public class MainActivity extends AppCompatActivity {
    public static final String FROM = "called from";
    public static final String MAIN_SRC = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences reader = getApplicationContext().getSharedPreferences(MyPreferences.MY_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = reader.edit();
        editor.putBoolean(MyPreferences.IS_FIRST_LOGIN, true);
        editor.apply();
        //Time to launch the next activity
        int TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                boolean isFirstRun = MyPreferences.isFirst(MainActivity.this);
                if (isFirstRun) {
                    //show start activity
                    i = new Intent(MainActivity.this, ChoosingActivity.class);
                } else {
                    boolean isRoommateSearcher = MyPreferences.isRoommateSearcher(MainActivity.this); //todo change activity
                    if (isRoommateSearcher) {
                        i = new Intent(MainActivity.this, ChoosingActivity.class);//todo change activity
                    } else {
                        i = new Intent(MainActivity.this, ChoosingActivity.class);

                    }
                    i.putExtra(MainActivity.FROM, MAIN_SRC);
                }
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
