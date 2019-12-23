package com.example.roome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  // todo edit
        switch(item.getItemId()) {
            case R.id.ab_action_matches:
                Toast.makeText(this, "matches", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.ab_action_s:
//                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
