package base.bot.api;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateHandler {

    final TelegramBot bot;

    @Autowired
    public UpdateHandler(TelegramBot telegramBot) {
        this.bot = telegramBot;
    }

    public void onWebhookUpdateReceived(String response){

    }



}
