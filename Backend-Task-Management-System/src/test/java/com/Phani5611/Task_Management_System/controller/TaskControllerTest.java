package com.Phani5611.Task_Management_System.controller;

import com.Phani5611.Task_Management_System.Controller.TaskController;
import com.Phani5611.Task_Management_System.Model.ApiResponse;
import com.Phani5611.Task_Management_System.Model.Task;
import com.Phani5611.Task_Management_System.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class TaskControllerTest {


    TaskController taskController;

    TaskService taskService;

    @BeforeEach
    void setUp(){
        taskController = new TaskController();
        taskService = new TaskService();

    }
    @Test
    public void createTask(){
        Task task = new Task();
        task.setTaskId(10100);
        task.setCreatedAt("2024-11-15 16:00:00");
        task.setDescription("learn JUNIT");
        ApiResponse response = new ApiResponse();
        response = taskService.createTask(task);
       assertEquals(200,response.getStatusCode());
    }
}
