package com.wellnes360.Task_Management_System.Service;
/* Author - Phani Sai Srinivas Madiraju ( Phani5611 ) */
import com.wellnes360.Task_Management_System.DAO.TaskRepo;
import com.wellnes360.Task_Management_System.Exceptions.InputFieldException;
import com.wellnes360.Task_Management_System.Exceptions.TaskFoundException;
import com.wellnes360.Task_Management_System.Model.ApiResponse;
import com.wellnes360.Task_Management_System.Model.Task;
import com.wellnes360.Task_Management_System.Exceptions.TaskNotFoundException;
import com.wellnes360.Task_Management_System.TaskValidation.InputFieldsValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.wellnes360.Task_Management_System.Model.Task.Status.COMPLETED;

@Service
public class TaskService {

    //Connects Repo and Service Layer
    @Autowired
    private TaskRepo repo;

    @Autowired
    private InputFieldsValidation validation;


    //Fetch All Tasks
    public List<Task> getAllTasks() {
        List<Task>tasks = repo.findAll();
        return  tasks;
    }

    //Fetch Task By ID
    public Optional<Task> getTask(long taskId) {
       Optional<Task> task =  repo.findById(taskId);
       return  task;
    }


    // TASK CREATION SERVICE

    /* 201 - CREATED, 400-MISSING INPUT FIELDS,409 - TASK ALREADY EXSISTS,  500 - UNEXPECTED ERROR*/
    public ApiResponse createTask(Task createTask) {

        String validMessage = validation.taskValidation(createTask);
        try{
            //Checking for missing input fields
            if (!"Valid".equals(validMessage)) {
                throw  new InputFieldException(validMessage);
            }

            //Checking if task exsits or not
            if(repo.existsById(createTask.getTaskId())) {
                throw  new TaskFoundException("Task - "+ createTask.getTaskId()+"  Already Exsists" );
            }

            // If Task Not Exists in DB - Creates New Task ( One at time as mentioned in the requirement )
            repo.save(createTask);
            //If everything is good
            return new ApiResponse(201,"Task Created");
        }
        catch (InputFieldException inputException){
            System.out.println("Missing input fields :" + inputException.getMessage());
            return new ApiResponse(400,inputException.getMessage());
        }
        catch( TaskFoundException taskFoundException){
            System.out.println(taskFoundException.getMessage());
            return new ApiResponse(409,taskFoundException.getMessage());// Task Already Exists
        }
        catch (Exception e){
            System.out.println("Unexpected exception in service block of task creation"+e.getMessage());
            return new ApiResponse(500,e.getMessage()); //Internal Server Error - Unexpected Error
        }
    }



    //TASK UPDATION SERVICE
    //200 - UPDATION SUCCESS, 400-MISSING INPUT FIELDS, 404 - TASK NOT FOUND TO UPDATE, 500 - UNEXPECTED ERROR
    public ApiResponse updateTask(long taskId, Task updateTask) {
        String inputValidation = validation.taskValidation(updateTask);

        try{
            if(!"Valid".equals(inputValidation)){
                throw new InputFieldException("Missing  "+inputValidation+" input to update.");
            }
            //If task not exsist throw an exception
            else if(!repo.findById(taskId).isPresent()){
                throw new TaskNotFoundException("TASK ID - "+ taskId +" NOT EXIST");
            }
            else{
               repo.save(updateTask);
               return new ApiResponse(200,"Task Updated"); // Update Success
            }
        }
        catch(InputFieldException inputUpdateFieldException){
            System.out.println(inputUpdateFieldException.getMessage());
            return new ApiResponse(400,inputUpdateFieldException.getMessage()); //Bad Request Missing Input Fields
        }
        catch(TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
            return new ApiResponse(404,taskNotFoundException.getMessage());// Task Not Found to Update
        }
        catch (Exception e){
            System.out.println("something went wrong in task updation service block"+e.getMessage());
            return new ApiResponse(500,e.getMessage()); //Internal Server Error - Unexpected Error
        }

    }



    //SERVICE DELETE TASK
    //200 - DELETION SUCCESS,  404 - TASK NOT FOUND TO DELETE, 500 - UNEXPECTED ERROR
    public ApiResponse deleteTask(long taskId) {
        try {
            if (repo.findById(taskId).isPresent()) {
                repo.deleteById(taskId);
                return new ApiResponse(200,"Task Deleted");// Task Deleted 200
            }
            throw new TaskNotFoundException("Task - " + taskId + " Not Found to delete");
        }
        catch (TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
            return new ApiResponse(404,taskNotFoundException.getMessage());// Task Not Found
        }
        catch (Exception e) {
            System.out.println("Something went wrong in Service Delete Task Block"+e.getMessage());
            return new ApiResponse(500,e.getMessage());//Internal Server Error - Unexpected Error
        }
    }


      // Mark Task Status
      //200 - MARK SUCCESS, 404 - TASK NOT FOUND TO MARK STATUS, 500 - UNEXPECTED ERROR
    public ApiResponse markTask(long taskId) {
        try{
            Optional<Task> taskOptional = repo.findById(taskId);
            if (taskOptional.isPresent()){
                    Task task = taskOptional.get();
                    task.setStatus(COMPLETED);
                    repo.save(task);
                    return new ApiResponse(200,"Task Marked"); // Successfully Marked
            }
            else{
                throw new TaskNotFoundException("Task - "+ taskId + " Not Found to Mark.");
            }
        }
        catch(TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
            return new ApiResponse(404,taskNotFoundException.getMessage()); // Task Not Found to Mark
        }
        catch (Exception e){
            System.out.println("Something went wrong in marking status service block"+e.getMessage());
            return new ApiResponse(500,e.getMessage()); //Internal Server Error - Unexpected Error
        }
    }
}
