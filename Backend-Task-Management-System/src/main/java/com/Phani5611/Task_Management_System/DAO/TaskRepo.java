package com.Phani5611.Task_Management_System.DAO;

import com.Phani5611.Task_Management_System.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TaskRepo extends JpaRepository<Task,Long> {
}
