package base.botapi.handlers;

import base.botapi.states.BotState;
import base.domain.Task;
import base.service.TaskService;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorksheetHandler implements InputMessageHandler {

    private final TaskService taskService;

    @Autowired
    public WorksheetHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public SendMessage handle(Message message) {

        long userId = message.from().id();
        long chatId = message.chat().id();

        List<Task> tasks = taskService.getUserTasks(userId);
        StringBuilder result = new StringBuilder();
        if (tasks.isEmpty())
            return new SendMessage(chatId, "Пусто :(");
        else {
            for (Task task : tasks
            ) {
                result.append("<i>" + task.getId() + "</i>" +  "\n" + task.getDescription() + "\n" + task.getExpiration() + "\n");
            }
        }
        return new SendMessage(chatId, result.toString()).parseMode(ParseMode.HTML);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_WORKSHEET;
    }
}
