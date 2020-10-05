package base.config;

import base.bot.MessageHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BotConfig {

    private final TelegramBot bot;
    private final MessageHandler messageHandler;

    @Value("${BOT_TOKEN}")
    private String BOT_TOKEN;

    @Value("${NGROK_URL}")
    private String NGROK_URL;

    public BotConfig() {
        this.bot = new TelegramBot(BOT_TOKEN);
        this.messageHandler = new MessageHandler(bot);
    }

    @PostConstruct
    public void configure() {
        System.out.println("Executing method after spring boot initialization");
        String URL = String.format("https://api.telegram.org/bot%s/setWebhook?url=%s",
                BOT_TOKEN, NGROK_URL);
        SetWebhook request = new SetWebhook()
                .url(URL);
        BaseResponse response = bot.execute(request);
        boolean ok = response.isOk();
        System.out.println(ok);

    }
}
