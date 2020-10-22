package base.botapi.handlers;

import base.botapi.states.BotState;
import base.cache.DataCache;
import base.domain.Task;
import base.domain.User;
import base.repo.TaskRepo;
import base.repo.UserRepo;
import base.service.TaskService;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LabHandler implements InputMessageHandler {

    private final DataCache dataCache;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;
    private final TaskService taskService;

    @Autowired
    public LabHandler(DataCache dataCache, TaskRepo taskRepo, UserRepo userRepo, TaskService taskService) {
        this.dataCache = dataCache;
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.taskService = taskService;
    }

//    @Override
//    public SendMessage handle(Message message) {
//        if (dataCache.getUsersCurrentBotState(message.from().id()).equals(BotState.ASK_LABS))
//                dataCache.setUsersCurrentBotState(message.from().id(), BotState.ASK_EXPIRATION);
//        dataCache.setUsersCurrentBotState(taskService.verifyTasks());
//        return processMessage(message);
//    }

    @Override
    public SendMessage handle(Message message) {
        String usrMessage = message.text();
        long userId = message.from().id();
        long chatId = message.chat().id();
        BotState botState = taskService.verifyTasks(userId);
        SendMessage replyMessage = null;

        if (botState.equals(BotState.ASK_EXPIRATION)){
            replyMessage = new SendMessage(chatId, "Укажите дату окончания работы:");
            dataCache.setUsersCurrentBotState(userId, BotState.ASK_DESCRIPTION);
        }
        if (botState.equals(BotState.ASK_DESCRIPTION)){
            Task task = new Task();
            task.setExpiration(LocalDateTime.now().plusMinutes(Long.parseLong(message.text())));
            User user = userRepo.findByTelegramId(userId);
            task.setUser(user);
            taskRepo.save(task);
            replyMessage = new SendMessage(chatId, "Укажите описание работы:");
            dataCache.setUsersCurrentBotState(userId, BotState.LAB_FILLED);
        }

        if (botState.equals(BotState.LAB_FILLED)){
            Task task = taskRepo.findByUserTelegramId(userId);
            task.setDescription(usrMessage);
            taskRepo.save(task);
            replyMessage = new SendMessage(chatId, "Заявка принята. Ожидайте подтверждения в течении 6 часов");
            dataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);
        }

        if (botState.equals(BotState.TASKS_LIMIT)){
            replyMessage = new SendMessage(chatId, "Превышен лимит по задачам");
            dataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);
        }
        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_LABS;
    }
}
