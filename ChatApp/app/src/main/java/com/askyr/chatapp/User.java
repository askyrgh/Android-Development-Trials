package com.askyr.chatapp;

import java.security.Key;

public class User {

    String userName;
    String email;
    String profilePicture;

    public User() {

    }

    public User(String userName, String email, String profilePicture) {
        this.userName = userName;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
