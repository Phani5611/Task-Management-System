package com.wellnes360.Task_Management_System.TaskValidation;

import com.wellnes360.Task_Management_System.Model.Task;
import org.springframework.stereotype.Component;

@Component
public class InputFieldsValidation {
    public String taskValidation(Task createTask){
        StringBuilder missingFields = new StringBuilder();
        try {
            if (createTask.getTaskId() == 0) {
                missingFields.append("Task ID is 0. ");
            }

            if (createTask.getTitle().isEmpty()) {
                missingFields.append("Title is null. ");
            }
            if (createTask.getDue_date() == null) {
                missingFields.append("Due date is null. ");
            }
            if (createTask.getDescription().isEmpty()) {
                missingFields.append("Description is empty. ");
            }
            if (createTask.getCreatedAt() == null) {
                missingFields.append("Created date empty . ");
            }
            if (createTask.getUpdatedAt() == null) {
                missingFields.append("Updated date empty . ");
            }
            if(createTask.getStatus()==null){
                missingFields.append("Status is empty");
            }
            if (missingFields.length() > 0) {
                return missingFields.toString().trim();
            }
            return "Valid";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

}
