package base.botapi.handlers;

import base.botapi.states.BotState;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class InfoHandler implements InputMessageHandler {

    private final String INFO = "Понимаю, что иногда просто не хватает времени на решение сложных задач \n" +
            "Поэтому всегда рад помочь тебе, нажми \"Заказать работу\"!)";
    private final String GITHUB_URL = "https://github.com/yep606/su_ai";

    @Override
    public SendMessage handle(Message message) {

        long chatId = message.chat().id();
        long userId = message.from().id();

        Keyboard inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton("Github source code").url(GITHUB_URL),
                        new InlineKeyboardButton("Donation").url("www.google.com")
                }
        );

        return new SendMessage(chatId, INFO).replyMarkup(inlineKeyboard);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.INFO;
    }
}
