package com.example.sproject.entities;

import java.util.Objects;

public class User {
    private Long id;
    private String username;

    private String email;
    private String password;
    private String photo;


    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, photo);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String icon_filename) {
        this.photo = icon_filename;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public User(String username, String email, String password, String icon_filename) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.photo = icon_filename;
    }

    public User(Long id, String username, String email, String password, String icon_filename) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.photo = icon_filename;
    }


}
