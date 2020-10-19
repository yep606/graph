package base.botapi.handlers;

import base.botapi.states.BotState;
import base.cache.DataCache;
import base.domain.Task;
import base.domain.User;
import base.repo.TaskRepo;
import base.repo.UserRepo;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LabHandler implements InputMessageHandler {

    private final DataCache dataCache;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Autowired
    public LabHandler(DataCache dataCache, TaskRepo taskRepo, UserRepo userRepo) {
        this.dataCache = dataCache;
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @Override
    public SendMessage handle(Message message) {
        if (dataCache.getUsersCurrentBotState(message.from().id()).equals(BotState.ASK_LABS))
                dataCache.setUsersCurrentBotState(message.from().id(), BotState.ASK_EXPIRATION);
        return processMessage(message);
    }

    private SendMessage processMessage(Message message) {
        String usrMessage = message.text();
        long userId = message.from().id();
        long chatId = message.chat().id();
        BotState botState = dataCache.getUsersCurrentBotState(userId);
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
            Keyboard keyboard = new ReplyKeyboardMarkup(
                    new KeyboardButton[]{
                            new KeyboardButton("Заказать работу"),
                            new KeyboardButton("О нас")
                    }
            ).oneTimeKeyboard(false);
            Task task = taskRepo.findByUserTelegramId(userId);
            task.setDescription(usrMessage);
            taskRepo.save(task);
            replyMessage = new SendMessage(chatId, "Заявка принята. Ожидайте подтверждения в течении 6 часов").replyMarkup(keyboard);
            dataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);
        }
        return replyMessage;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_LABS;
    }
}
