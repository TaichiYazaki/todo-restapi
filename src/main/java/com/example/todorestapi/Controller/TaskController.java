package com.example.todorestapi.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

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

    // タスク一覧取得API
    @Override
    public ResponseEntity<List<TaskDTO>> listTasks() {
        List<TaskEntity> entity = serivce.find();
        List<TaskDTO> dto = entity.stream()
                            .map(this::toTaskDTO) //this= インスタンスのメソッド(entity=service.find())
                            .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }


    // タスク登録API
    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form) {
        TaskEntity entity = serivce.create(form);
        TaskDTO dto = new TaskDTO();
        // ポストマンでテストの際に取得したIDを知るために利用開始
        dto.setId(entity.getId());
        // 終了
        dto.setText(entity.getText());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // タスクの削除
    @Override
    public ResponseEntity<Void> delete(@Min(1) Integer id) {
        serivce.delete(id);
        return ResponseEntity.noContent().build();
    }

    private TaskDTO toTaskDTO(TaskEntity entity){
        TaskDTO dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        return dto;
    }
}
