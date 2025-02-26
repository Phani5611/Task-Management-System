package com.Phani5611.Task_Management_System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Model class for custom data type of Task
@Entity
public class Task {

    @Id
    private long taskId;

    private String title;
    private String description;
    private LocalDate due_date;
    private Status status;
    private String createdAt;
    private LocalDateTime updatedAt;

    public enum Status{
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    public Task(){

    }

    public Task(long taskId, String title, String description, LocalDate  due_date, Status status, String createdAt, LocalDateTime updatedAt) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate  getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
