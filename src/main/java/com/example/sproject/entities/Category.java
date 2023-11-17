package com.example.sproject.entities;

public class Category {
    private int id;
    private String text;

    public Category(int id, String name) {
        this.id = id;
        this.text = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(String name) {
        this.text = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String name) {
        this.text = name;
    }
}
