package com.example.roome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class ChoosingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);
        Intent intent = getIntent();
        updateUserName(intent);
    }

    /**
     * The function displays the user's name (from which it got from the previous activity) in this
     * activity
     *
     * @param intent
     */
    private void updateUserName(Intent intent) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ChoosingActivity.this);
        String userName = "";
        if (acct != null) {
            userName = acct.getDisplayName();
        }
        TextView textView = findViewById(R.id.tv_hello_name);
        textView.setText(String.format("Hi %s!", userName));
    }
}
