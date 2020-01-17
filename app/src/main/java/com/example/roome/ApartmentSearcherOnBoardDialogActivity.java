package com.example.roome;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is a dialog themed activity for a new apartment searcher on boarding.
 */
public class ApartmentSearcherOnBoardDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_searcher_on_board);
    }

    /**
     * Closes the window when clicked ok or outside the window
     * @param okButton - The ok button in the dialog frame.
     */
    public void onClickOk(View okButton){
        finish();
    }
}
