package base.config;

import base.bot.MessageHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BotConfig {

    private final TelegramBot bot;
    private final MessageHandler messageHandler;

    public BotConfig() {
        this.bot = new TelegramBot(uploadToken());
        this.messageHandler = new MessageHandler(bot);
    }

    public void configure() {
        bot.setUpdatesListener(updates -> {
            messageHandler.process(updates);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private String uploadToken(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app-dev.properties";
        Properties appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProps.getProperty("BOT_TOKEN");

    }

}
