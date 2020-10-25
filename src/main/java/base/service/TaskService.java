package base.service;

import base.botapi.states.BotState;
import base.domain.Task;
import base.domain.User;
import base.repo.TaskRepo;
import base.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public boolean hasLimit(long userId){
        return userRepo.findByTelegramId(userId).get().getTaskCount() >= 2;
//        return true;
    }

    public void save(long userId, Task task) {
        User user = userRepo.findByTelegramId(userId).get();
        user.setTaskCount(user.getTaskCount() + 1);
        task.setUser(user);
        taskRepo.save(task);
    }
}
