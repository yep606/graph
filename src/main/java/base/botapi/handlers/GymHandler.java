package base.botapi.handlers;

import base.botapi.states.BotState;
import base.cache.DataCache;
import base.service.RestService;
import base.util.PaymentResponse;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GymHandler implements InputMessageHandler {

    private final DataCache dataCache;
    private final RestService restService;

    @Value("${PAYMENT_PAGE_URL}")
    private String PAYMENT_PAGE_URL;
    @Value("${STATUS_PAGE_URL}")
    private String STATUS_PAGE_URL;
    private final String PAYMENT_MESSAGE = "Нажми на ссылку ниже. ↓ \n После успешной оплаты напиши: \"Готово!\"";


    @Autowired
    public GymHandler(DataCache dataCache, RestService restService) {
        this.dataCache = dataCache;
        this.restService = restService;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.BUY_GYM;
    }

    @Override
    public SendMessage handle(Message message) {
        long userId = message.from().id();
        long chatId = message.chat().id();

        if (dataCache.getUsersCurrentBotState(userId).equals(BotState.BUY_GYM))
            dataCache.setUsersCurrentBotState(userId, BotState.GYM_PAYMENT);

        BotState botState = dataCache.getUsersCurrentBotState(userId);

        if (botState.equals(BotState.GYM_PAYMENT)){
            Keyboard inlineKeyboard = new InlineKeyboardMarkup(
                    new InlineKeyboardButton[]{
                            new InlineKeyboardButton("Ссылка на оплату").url(PAYMENT_PAGE_URL + userId),
                    }
            );

            dataCache.setUsersCurrentBotState(userId, BotState.WAITING_FOR_PAYMENT);
            return new SendMessage(chatId, PAYMENT_MESSAGE).replyMarkup(inlineKeyboard);
        }

        if (botState.equals(BotState.WAITING_FOR_PAYMENT)){
            if (message.text().equals("Готово!") && verifyPayment(userId)){
                dataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);
                return new SendMessage(chatId, "Типо отправил файл --файл--");
            }
            return new SendMessage(chatId, "Произошла ошибка, попробуйте еще раз или подождите. А может я просто наебал вас на деньги");

        }

        return new SendMessage(chatId, "Хуй");
    }

    private boolean verifyPayment(Long userId) {
        return restService.getPaymentInfo(userId) != null;
    }

}
