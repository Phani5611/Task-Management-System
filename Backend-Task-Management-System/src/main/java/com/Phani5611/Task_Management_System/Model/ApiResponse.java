package com.Phani5611.Task_Management_System.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private  int statusCode;
    private  String message;
    private List<T> data;

    /*public  ApiResponse(){}
    public  ApiResponse(int statusCode,String message){
        this.statusCode=statusCode;
        this.message=message;
    }*/

    /*public int getStatusCode() {
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
    }*/
}
