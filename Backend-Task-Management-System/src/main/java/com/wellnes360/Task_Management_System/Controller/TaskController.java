package com.wellnes360.Task_Management_System.Controller;

import com.wellnes360.Task_Management_System.Model.Task;
import com.wellnes360.Task_Management_System.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    // Fetch All TASK  Mapper
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
      List<Task> allTasks = service.getAllTasks();
      if(allTasks.isEmpty()){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allTasks);
      }
      return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    // Fetch TASK By ID Mapper
    @GetMapping("/tasks/{taskId}")
    public Optional<Task> getTask(@PathVariable long taskId){
        return  service.getTask(taskId);
    }


    // TASK Creation Mapper
    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody Task createTask){
          boolean response = service.createTask(createTask);
          if(response){
              System.out.println("Creation Susccessfully - Msg from controller");
              return ResponseEntity.status(HttpStatus.CREATED).build();
          }
          System.out.println(" Unsuccessful - From Controller");
          return  ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
    }


    // TASK Update Mapper
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable long taskId, @RequestBody Task updateTask ){
       boolean response=service.updateTask(taskId,updateTask);
       if(response){
           System.out.println("Updated Successfully - From Controller");
           return ResponseEntity.status(HttpStatus.ACCEPTED).build();
       }
        System.out.println("Not Updated - From Controller");
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // TASK Delete Mapper
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable long taskId){
       boolean response =  service.deleteTask(taskId);
       if(!response){
           System.out.println("TASK NOT EXIST - Controller");
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
        System.out.println("TASK DELETED - Controller");
       return ResponseEntity.status(HttpStatus.OK).build();
    }

    //TASK Status Mapper
    @PatchMapping("/tasks/{taskId}")
    public  ResponseEntity<Void> markTask(@PathVariable long taskId){
       boolean responseStatus =  service.markTask(taskId);
        if(!responseStatus){
            System.out.println("TASK NOT EXIST - Controller");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        System.out.println("TASK MARKED - Controller");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
