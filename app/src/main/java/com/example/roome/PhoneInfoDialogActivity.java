package com.example.roome;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is a dialog themed activity that pops up when the user clicks on the 'i' sign near the phone
 * number edit text in the profile page. This dialog informs the user that his phone number will only
 * be presented after a match between the roommate searcher and the apartment searcher.
 */
public class PhoneInfoDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info_dialog);
    }

    /**
     * Closes the window when clicked ok or outside the window
     * @param okButton - The ok button in the dialog frame.
     */
    public void onClickOk(View okButton){
        finish();
    }
}
