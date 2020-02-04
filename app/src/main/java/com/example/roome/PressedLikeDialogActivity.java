package com.example.roome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class PressedLikeDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressed_like_dialog);
    }

    /**
     * Closes the window when clicked ok or outside the window
     * @param okButton - The ok button in the dialog frame.
     */
    public void onClickOkLike(View okButton){
        finish();
    }
}
