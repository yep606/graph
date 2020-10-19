package base.botapi;

import base.botapi.states.BotState;
import base.cache.DataCache;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UpdateProcessor {

    private final TelegramBot bot;
    private final DataCache dataCache;
    private final BotContext botContext;

    @Autowired
    public UpdateProcessor(TelegramBot telegramBot, DataCache dataCache, BotContext botContext) {
        this.bot = telegramBot;
        this.dataCache = dataCache;
        this.botContext = botContext;
    }

    public void onUpdateReceived(Update update){
        if(update.inlineQuery() != null)
            System.out.println(update.inlineQuery().query());
        else {
            SendMessage replyMessage = null;
            Message message = update.message();
            if (message != null && !"".equals(message.text())) {
                log.info("New message from User:{}, chatId: {},  with text: {}",
                        message.from().username(), message.chat().id(), message.text());
                replyMessage = handleInputMessage(message);
            }
            bot.execute(replyMessage);
        }
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.text();
        int userId = message.from().id();
        BotState botState;
        SendMessage replyMessage;
        switch (inputMsg) {
            case "/start":
                botState = BotState.MAIN_MENU;
                break;
            case "Заказать работу":
                botState = BotState.ASK_LABS;
                break;
            case "О нас":
                botState = BotState.INFO;
                break;
            default:
                botState = dataCache.getUsersCurrentBotState(userId);
                break;
        }

        dataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botContext.processInputMessage(botState, message);
        return replyMessage;
    }


}
