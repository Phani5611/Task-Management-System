package com.wellnes360.Task_Management_System.Controller;

import com.wellnes360.Task_Management_System.Model.Task;
import com.wellnes360.Task_Management_System.Service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin()
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
      return new ResponseEntity<>(allTasks,HttpStatus.OK);
    }




    // Fetch TASK By ID Mapper
    @GetMapping("/tasks/{taskId}")
    public Optional<Task> getTask(@PathVariable long taskId){
        return  service.getTask(taskId);
    }


    // TASK Creation Mapper
    @PostMapping("/tasks")
    public ResponseEntity<String> createTask(@RequestBody Task createTask){
          boolean response = service.createTask(createTask);
          if(response){
              System.out.println("Creation Susccessfully - Msg from controller");
              return new ResponseEntity<>("Task Created",HttpStatus.CREATED);
          }
          System.out.println(" Unsuccessful - From Controller");
          return  new ResponseEntity<>("Task Already Exists",HttpStatus.CONFLICT);
    }


    // TASK Update Mapper
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable long taskId, @RequestBody Task updateTask ){
       boolean response=service.updateTask(taskId,updateTask);
       if(response){
           System.out.println("Updated Successfully - From Controller");
           return new ResponseEntity<>("Task Updated",HttpStatus.OK);
       }
        System.out.println("Not Updated - From Controller");
       return new ResponseEntity<>("Task Not Updated / Not Exists",HttpStatus.NOT_FOUND);
    }


    // TASK Delete Mapper
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long taskId){
       boolean response =  service.deleteTask(taskId);
       if(!response){
           System.out.println("TASK NOT EXIST - Controller");
           return new ResponseEntity<>("Task doesn't Exists",HttpStatus.NOT_FOUND);
       }
        System.out.println("TASK DELETED - Controller");
        return new ResponseEntity<>("Task Deleted",HttpStatus.OK);
    }


    //TASK Status Mapper -> { 0-Pending , 1-In_Progress , 2-Completed }
    @PatchMapping("/tasks/{taskId}")
    public  ResponseEntity<String> markTask(@PathVariable long taskId){
       boolean responseStatus =  service.markTask(taskId);
        if(!responseStatus){
            System.out.println("TASK NOT EXIST - Controller");
            return new ResponseEntity<>("Task doesn't Exists",HttpStatus.NOT_FOUND);
        }
        System.out.println("TASK MARKED - Controller");
        return new ResponseEntity<>("Task Marked",HttpStatus.OK);
    }

}
