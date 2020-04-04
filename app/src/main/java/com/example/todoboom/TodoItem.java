package com.example.todoboom;

public class TodoItem {
    String name;

    public TodoItem(String name) {
        this.name = name;
    }

    private static int lastContactId = 0;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
