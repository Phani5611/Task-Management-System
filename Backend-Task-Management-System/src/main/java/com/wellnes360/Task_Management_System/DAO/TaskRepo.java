package com.wellnes360.Task_Management_System.DAO;

import com.wellnes360.Task_Management_System.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TaskRepo extends JpaRepository<Task,Long> {
}
