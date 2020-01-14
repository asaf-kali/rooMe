package com.example.roome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.roome.R;

/**
 * this is a dialog themed activity for alerting in the EditProfileActivity when some fields are not
 * filled correctly.
 */
public class EditProfileAlertDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_alert_dialog);
    }

    /**
     * closes the window when clicked ok or outside the window
     * @param okButton
     */
    public void onClickOk(View okButton){
        finish();
    }
}
