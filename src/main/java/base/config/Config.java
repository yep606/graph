package base.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public TelegramBot telegramBbot(BotProperties botProperties) {
        return new TelegramBot(botProperties.getApiKey());
    }

}
