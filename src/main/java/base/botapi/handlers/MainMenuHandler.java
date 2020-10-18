package base.botapi.handlers;

import base.botapi.states.BotState;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class MainMenuHandler implements InputMessageHandler {

    @Override
    public SendMessage handle(Message message) {

        long id = message.from().id();
        long chatId = message.chat().id();

        Keyboard keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton("Заказать работу"),
                        new KeyboardButton("О нас")
                }
        ).oneTimeKeyboard(false);


        return new SendMessage(chatId, "Главное меню").replyMarkup(keyboard);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;
    }
}
