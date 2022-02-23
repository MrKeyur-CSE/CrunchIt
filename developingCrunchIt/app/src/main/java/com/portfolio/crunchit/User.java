package com.portfolio.crunchit;

public class User {
    public String fullName;
    public String email;
    public String number;

    public User(){

    }

    public User(String Name, String email, String phNumber){
        this.fullName = Name;
        this.email = email;
        this.number = phNumber;
    }
}
