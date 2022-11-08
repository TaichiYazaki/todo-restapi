package com.example.todorestapi.Controller;


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

    String url = "http://localhost:8080/tasks/";

    @RequestMapping("/")
    public String index(TaskForm form, Model model) {
        
        ResponseEntity<TaskEntity[]> list = restTemplate.getForEntity(url, TaskEntity[].class);
        TaskEntity[] lists = list.getBody();
        model.addAttribute("list", lists);

        //これでもjsonのリストを取得できる
        /*ResponseEntity<List<TaskEntity>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TaskEntity>>() {
                });
        List<TaskEntity> list = response.getBody();
        model.addAttribute("list", list);
        */
        return "main";
    }

    @RequestMapping("/create-task")
    public String createTask(TaskForm form) {
        TaskEntity entity = new TaskEntity();
        entity.setStore(form.getStore());
        entity.setPrice(form.getPrice());
        restTemplate.postForObject(url, entity, TaskEntity.class);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        System.out.println(id);
        return "redirect:/";
    }

}
