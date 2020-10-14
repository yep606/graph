package base.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BotConfig {

    @Value("${BOT_TOKEN}")
    private String BOT_TOKEN;

    @Value("${NGROK_URL}")
    private String NGROK_URL;

    @Bean
    public TelegramBot telegramBot(){
        TelegramBot bot = new TelegramBot(BOT_TOKEN);
        System.out.println("Executing method after spring boot initialization");
        String URL = String.format("https://api.telegram.org/bot%s/setWebhook?url=%s",
                BOT_TOKEN, NGROK_URL);
        SetWebhook request = new SetWebhook()
                .url(URL);
//        BaseResponse response = bot.execute(request);
//        boolean ok = response.isOk();
//        System.out.println(ok);
        return bot;
    }

}
