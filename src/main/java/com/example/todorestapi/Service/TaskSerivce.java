package com.example.todorestapi.Service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todorestapi.Entity.TaskEntity;
import com.example.todorestapi.Repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskSerivce {

    @Autowired
    private final TaskRepository repository;

    public TaskEntity create(TaskForm form) {
        TaskEntity entity = new TaskEntity();
        entity.setStore(form.getStore());
        entity.setPrice(form.getPrice());
        return repository.save(entity);
    }

}
