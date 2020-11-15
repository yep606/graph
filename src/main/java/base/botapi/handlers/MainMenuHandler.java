package base.botapi.handlers;

import base.botapi.states.BotState;
import base.domain.User;
import base.repo.UserRepo;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuHandler implements InputMessageHandler {

    private final UserRepo userRepo;

    @Autowired
    public MainMenuHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public SendMessage handle(Message message) {
        long userId = message.from().id();
        long chatId = message.chat().id();

        if (!userRepo.findByTelegramId(userId).isPresent()){
            User newUser = new User();
            newUser.setFirstName(message.from().firstName());
            newUser.setLastName(message.from().lastName());
            newUser.setUsername(message.from().username());
            newUser.setTelegramId(userId);
            userRepo.save(newUser);
        }

        Keyboard keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton("Заказать работу"),
                        new KeyboardButton("О нас"),
                        new KeyboardButton("Текущие работы"),
                }
        ).oneTimeKeyboard(false);


        return new SendMessage(chatId, "Главное меню").replyMarkup(keyboard);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;
    }
}
