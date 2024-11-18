package com.wellnes360.Task_Management_System.Service;
/* Author - Phani Sai Srinivas Madiraju ( Phani5611 ) */
import com.wellnes360.Task_Management_System.DAO.TaskRepo;
import com.wellnes360.Task_Management_System.Model.Task;
import com.wellnes360.Task_Management_System.Model.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.wellnes360.Task_Management_System.Model.Task.Status.COMPLETED;

@Service
public class TaskService {

    //Conects Repo and Service Layer
    @Autowired
    private TaskRepo repo;


    //Fetch All TASKS
    public List<Task> getAllTasks() {
        List<Task>tasks = repo.findAll();
        return  tasks;
    }

    //Fetch Task By ID
    public Optional<Task> getTask(long taskId) {
       Optional<Task> task =  repo.findById(taskId);
       return  task;
    }

    // TASK Creation
    public boolean  createTask(Task createTask) {
        try{
                if(repo.existsById(createTask.getTaskId())) {
                    throw  new Exception("Task ID"+createTask.getTaskId()+" Already Exists - MSG from Service");
                }
                // If Task Not Exists in DB - Creates New Task ( One at time as mentioned in the requiremnet )
                repo.save(createTask);
        }
        catch (Exception e){
            System.out.println("Error " + e.getMessage());;
            return  false;
        }
        return  true;
    }


    //Update TASK
    public boolean updateTask(long taskId, Task updateTask) {
        try{
            if(repo.findById(taskId).isPresent()){
               repo.save(updateTask);
               return true;
            }
            else{
                throw new RuntimeException("TASK ID - "+ updateTask.getTaskId()+" NOT EXIST");
            }
        }
        catch (Exception e){
            System.out.println("Error -"+e.getMessage()+" - From Service");
        }
        return false;
    }


    //Delete TASK
    public boolean deleteTask(long taskId) {
        try {
            if (repo.findById(taskId).isPresent()) {
                repo.deleteById(taskId);
                return true;
            }
        } catch (TaskNotFoundException e) {
            System.out.println("Task ID - " + e + "Doesn't exsist in DB");
        }
        return false;
    }


      // Mark Task Status
    public boolean markTask(long taskId) {
        try{
            if(repo.findById(taskId).isPresent()) {
                Optional<Task> taskOptional = repo.findById(taskId);
                if(taskOptional.isPresent()){
                    Task task = taskOptional.get();
                    task.setStatus(COMPLETED);
                    repo.save(task);
                    return true;
                }
            }
        }
        catch(Exception e){
            System.out.println("Not Marked"+ e.getMessage());
        }
        return  false;
    }
}
