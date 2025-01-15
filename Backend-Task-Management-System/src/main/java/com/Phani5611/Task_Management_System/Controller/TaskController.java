package com.Phani5611.Task_Management_System.Controller;

import com.Phani5611.Task_Management_System.Model.ApiResponse;
import com.Phani5611.Task_Management_System.Model.Task;
import com.Phani5611.Task_Management_System.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
      return new ResponseEntity<>(allTasks,HttpStatus.OK);
    }


    // Fetch TASK By ID Mapper
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Optional<Task>> getTask(@PathVariable long taskId){
        Optional<Task> findTaskById = service.getTask(taskId);
        if(findTaskById.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findTaskById);
        return  new ResponseEntity<>(findTaskById,HttpStatus.OK);
    }


    // TASK Creation Mapper
    @PostMapping("/tasks")
    public ResponseEntity<ApiResponse> createTask(@RequestBody (required = false) Task createTask){
        //Checking if Request Body is null
        if(createTask==null){
            ApiResponse errorResponse = new ApiResponse(400,"No JSON Input");
            return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(errorResponse.getStatusCode()));
        }
        ApiResponse responseCreation = service.createTask(createTask);
        return  new ResponseEntity<>(responseCreation,HttpStatus.valueOf(responseCreation.getStatusCode()));
    }


    // TASK Update Mapper
    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable long taskId, @RequestBody( required = false) Task updateTask ){
        //Checking if Request Body is null
        if(updateTask==null){
            ApiResponse errorResponse = new ApiResponse(400,"No JSON Input");
            return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(errorResponse.getStatusCode()));
        }
       ApiResponse responseUpdate = service.updateTask(taskId,updateTask);
       return  new ResponseEntity<>(responseUpdate,HttpStatus.valueOf(responseUpdate.getStatusCode()));
    }


    // TASK Delete Mapper
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable long taskId){
       ApiResponse responseDelete =  service.deleteTask(taskId);
       return  new ResponseEntity<>(responseDelete,HttpStatus.valueOf(responseDelete.getStatusCode()));
    }


    //TASK Status Mapper -> { 0-Pending , 1-In_Progress , 2-Completed }
    @PatchMapping("/tasks/{taskId}")
    public  ResponseEntity<ApiResponse> markTask(@PathVariable long taskId){
     ApiResponse responseStatus =  service.markTask(taskId);
        return  new ResponseEntity<>(responseStatus,HttpStatus.valueOf(responseStatus.getStatusCode()));
    }

}
