package com.attijariLeasing.appBackend.user;

import lombok.Data;


//create login class for define how login data send by front looks like and create an object for test user login
@Data
public class LoginObject {

    private String email;
    private String password;

    public LoginObject() {
    }

    public LoginObject(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
