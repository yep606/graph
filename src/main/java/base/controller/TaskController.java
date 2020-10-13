package base.controller;

import base.domain.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/task")
    public Task mainPage(){

        return new Task("New task!");
    }

}
