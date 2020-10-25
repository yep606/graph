package base.botapi.handlers;

import base.botapi.states.BotState;
import base.cache.DataCache;
import base.domain.Task;
import base.repo.TaskRepo;
import base.service.TaskService;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LabHandler implements InputMessageHandler {

    private final DataCache dataCache;
    private final TaskService taskService;

    @Autowired
    public LabHandler(DataCache dataCache, TaskService taskService) {
        this.dataCache = dataCache;
        this.taskService = taskService;
    }

    @Override
    public SendMessage handle(Message message) {
        long userId = message.from().id();
        long chatId = message.chat().id();

        if (dataCache.getUsersCurrentBotState(userId).equals(BotState.ASK_LABS))
            dataCache.setUsersCurrentBotState(userId, BotState.ASK_EXPIRATION);


        SendMessage replyMessage = null;
        BotState botState = dataCache.getUsersCurrentBotState(userId);
        if(taskService.hasLimit(userId))
            botState = BotState.TASKS_LIMIT;
        System.out.println(botState);
        Task task = dataCache.getUsersTask(userId);

        if (botState.equals(BotState.ASK_EXPIRATION)) {
            replyMessage = new SendMessage(chatId, "Сколько дней для сдачи?");
            dataCache.setUsersCurrentBotState(userId, BotState.ASK_DESCRIPTION);
            dataCache.setUserTask(userId, task);
        }

        if (botState.equals(BotState.ASK_DESCRIPTION)){
            task.setExpiration(LocalDateTime.now().plusMinutes(Long.parseLong(message.text())));
            replyMessage = new SendMessage(chatId, "Укажите описание работы:");
            dataCache.setUsersCurrentBotState(userId, BotState.LAB_FILLED);
            dataCache.setUserTask(userId, task);
        }

        if (botState.equals(BotState.LAB_FILLED)){
            task.setDescription(message.text());
            taskService.save(userId, task);
            replyMessage = new SendMessage(chatId, "Заявка принята. Ожидайте подтверждения в течении 6 часов");
            dataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);
            dataCache.setUserTask(userId, null);
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
