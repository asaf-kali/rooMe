package com.example.todoboom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnCreateOnClick(View view) {
        EditText et = findViewById(R.id.etInputText);
        TextView tv = findViewById(R.id.tvShowInputText);
        tv.setText(et.getText());
        et.setText("");
    }
}
