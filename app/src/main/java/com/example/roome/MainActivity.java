package com.example.roome;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * this activity is presenting the app logo for a few seconds and then disappears.
 * this activity will be shown on every app visit.
 */
public class MainActivity extends AppCompatActivity {
    private static final String FROM = "called from";
    private static final String MAIN_SRC = "MAIN";
    protected static final int MIN_SUPPORTED_API_LEVEL = 20;
    //Time passed till next activity is launched
    private static final int TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMediate.setDataSnapshot();
        RoommateSearcherInfoConnector.initD();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyPreferences.getUserUid(getApplicationContext()) == null) {
                    // Not signed in, launch the Sign In activity
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                    finish();
                } else {
                    startActivityWithAnimation();
                    finish();
                }
            }
        }, TIME_OUT);
    }

    /**
     * This method starts a new activity and adding the transition animation for relevant versions
     * of android
     */
    public void startActivityWithAnimation() {
        Intent i;
        boolean isFirstTime = MyPreferences.isFirstTime(MainActivity.this);
        if (isFirstTime) {
            //show home activity
            i = new Intent(MainActivity.this, ChoosingActivity.class);
        } else {
            boolean isRoommateSearcher = MyPreferences.isRoommateSearcher(MainActivity.this);
            if (isRoommateSearcher) {
                i = new Intent(MainActivity.this, MainActivityRoommateSearcher.class);
            } else {
                i = new Intent(MainActivity.this, MainActivityApartmentSearcher.class);

            }
            i.putExtra(MainActivity.FROM, MAIN_SRC);
        }
        if (Build.VERSION.SDK_INT > MIN_SUPPORTED_API_LEVEL) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }
    }
}
