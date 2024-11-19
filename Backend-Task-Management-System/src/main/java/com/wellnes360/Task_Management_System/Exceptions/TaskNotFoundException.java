package com.wellnes360.Task_Management_System.Exceptions;


public class TaskNotFoundException extends RuntimeException{
    public  TaskNotFoundException (String message){
        super(message);
    }
}
