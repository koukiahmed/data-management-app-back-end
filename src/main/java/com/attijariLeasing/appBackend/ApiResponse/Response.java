package com.attijariLeasing.appBackend.ApiResponse;

import lombok.Data;

@Data
public class Response {
    String message;
    boolean status;

    public Response(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
