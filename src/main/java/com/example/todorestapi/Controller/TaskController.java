package com.example.todorestapi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todorestapi.Entity.TaskEntity;
import com.example.todorestapi.Service.TaskSerivce;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    @Autowired
    private final TaskSerivce serivce;

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form) {
        TaskEntity entity = serivce.create(form);
        TaskDTO dto = new TaskDTO();
        //ポストマンのIDを知るために利用開始
        dto.setId(entity.getId());
        //終了
        dto.setStore(entity.getStore());
        dto.setPrice(entity.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    

    
}
