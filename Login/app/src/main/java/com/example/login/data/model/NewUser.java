package com.example.login.data.model;

public class NewUser {

    private String firstLastName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;

    public NewUser(String firstLastName, String userName, String password, String email, String phoneNumber) {
        this.firstLastName = firstLastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
