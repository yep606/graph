package base.controller;

import base.domain.Task;
import base.repo.TaskRepo;
import base.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Autowired
    public TaskController(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/tasks")
    public List<Task> mainPage(){
        return taskRepo.findAll();
    }

}
