package com.example.todorestapi.Controller;

import java.util.List;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.todoapi.model.TaskForm;
import com.example.todorestapi.Entity.TaskEntity;

@Controller
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/")
    public String index(TaskForm form, Model model) {
        String url = "http://localhost:8080/tasks/";
        ResponseEntity<List<TaskEntity>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TaskEntity>>() {
                });
        List<TaskEntity> list = response.getBody();
        model.addAttribute("list", list);
        return "main";
         
    }

    @RequestMapping("/create-task")
    public String createTask(TaskForm form) {
        String url = "http://localhost:8080/tasks/";
        TaskEntity entity = new TaskEntity();
        entity.setStore(form.getStore());
        entity.setPrice(form.getPrice());
        restTemplate.postForObject(url, entity, TaskEntity.class);
        return "redirect:/";
    }

}
