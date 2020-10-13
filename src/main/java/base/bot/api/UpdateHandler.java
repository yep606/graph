package base.bot.api;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UpdateHandler {

    final TelegramBot bot;

    @Autowired
    public UpdateHandler(TelegramBot telegramBot) {
        this.bot = telegramBot;
    }

    public void onWebhookUpdateReceived(String response){
        
        Update update = BotUtils.parseUpdate(response);
        if(update.message().text().equals("hello")){
            bot.execute(new SendMessage(update.message().chat().id(), "Answer back!"), new Callback() {
                @Override
                public void onResponse(BaseRequest request, BaseResponse response) {

                }
                @Override
                public void onFailure(BaseRequest request, IOException e) {

                }
            });
        }

    }



}
