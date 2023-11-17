package com.example.sproject.service;

public class ValidationRegisterDataHelper {
    public static boolean isEmailValid(String email) {
        return !email.isEmpty()
                && email.length() <= 255
                && email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }

    public static boolean isUsernameValid(String username) {
        return username.length() >= 4
                && username.length() <= 70
                && username.matches("^[a-zA-Z0-9а-яА-Я_\\-]+$");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 8
                && password.length() <= 64;
    }
}
