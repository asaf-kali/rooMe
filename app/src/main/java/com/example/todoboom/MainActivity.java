package com.example.todoboom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    RecyclerView recyclerView = a;
    ArrayList<TodoItem> todoItems;
    // Lookup the recyclerview in activity layout
    RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
    // Create adapter passing in the sample user data
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize contacts
        todoItems = new ArrayList<TodoItem>();
        ContactsAdapter adapter = new ContactsAdapter(todoItems);

        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

    public void btnCreateOnClick(View view) {
        EditText et = findViewById(R.id.etInputText);
        TodoItem todoItem = new TodoItem(et.getText().toString());
        todoItems.add(todoItem);
        et.setText("");
    }
}
