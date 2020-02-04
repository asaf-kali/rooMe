package com.example.roome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class PressedUnlikeDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressed_unlike_dialog);
    }


    /**
     * Closes the window when clicked ok or outside the window
     * @param okButton - The ok button in the dialog frame.
     */
    public void onClickOkUnlike(View okButton){
        finish();
    }
}
