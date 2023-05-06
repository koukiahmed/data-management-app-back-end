package com.attijariLeasing.appBackend.user;

import lombok.Data;

//create login response template to send it to front for testing user login and send role of user
@Data
public class LoginResponse {

    String message;
    boolean status;
    String email;
    String role;


    public LoginResponse(String message, boolean status, String email,String role) {
        this.message = message;
        this.status = status;
        this.email = email;
        this.role = role;
    }
}
