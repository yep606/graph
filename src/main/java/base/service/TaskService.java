package base.service;

import base.botapi.states.BotState;
import base.cache.DataCache;
import base.domain.Task;
import base.repo.TaskRepo;
import base.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public BotState verifyTasks(long userId){
        if (userRepo.findById(userId).get().getTaskCount() >= 2)
            return BotState.TASKS_LIMIT;

        Optional<Task> checkTask = taskRepo.findByUserTelegramIdAndFilled(userId, false);
        if (checkTask.isPresent()){
            Task task = checkTask.get();
            if (task.getExpiration() == null)
                return BotState.ASK_EXPIRATION;
            else
                return BotState.ASK_DESCRIPTION;

        }
        else
            return BotState.ASK_EXPIRATION;

    }


}
