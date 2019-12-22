package com.example.roome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

public class MainActivityApartmentSearcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_apartment_searcher);
//        Toolbar apartment_toolbar = (Toolbar) findViewById(R.id.apartment_tool_bar);
        androidx.appcompat.widget.Toolbar apartment_toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.apartment_tool_bar);
        setSupportActionBar(apartment_toolbar);
    }
}
