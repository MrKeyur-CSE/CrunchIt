package com.portfolio.crunchit.Abstract;

import java.util.List;

public class User {
    public String fullName;
    public String email;
    public String number;
    public List<String> fcmtokens;

    public User(){

    }

    public User(String Name, String Email, String phNumber, List<String> FCMTokens){
        this.fullName = Name;
        this.email = Email;
        this.number = phNumber;
        this.fcmtokens = FCMTokens;
    }
}
