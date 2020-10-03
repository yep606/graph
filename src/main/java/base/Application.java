package base;

import base.config.BotConfig;

import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) {
        Logger logger=Logger.getLogger("global");
        new BotConfig().configure();
        logger.info("Bot started");
    }

}
