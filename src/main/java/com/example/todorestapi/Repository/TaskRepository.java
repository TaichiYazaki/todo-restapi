package com.example.todorestapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todorestapi.Entity.TaskEntity;

public interface TaskRepository extends JpaRepository <TaskEntity, String> {
    
}
