package base.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.List;

public class MessageHandler {

    private final TelegramBot bot;

    public MessageHandler(TelegramBot bot) {
        this.bot = bot;
    }

    public void process(List<Update> updates) {
        updates.forEach(update -> bot.execute(new SendMessage(update.message().chat().id(),"Hello, world!")));
    }
}
