package com.Phani5611.Task_Management_System.Model;

public class ApiResponse {
    private  int statusCode;
    private  String message;

    public  ApiResponse(int statusCode,String message){
        this.statusCode=statusCode;
        this.message=message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
