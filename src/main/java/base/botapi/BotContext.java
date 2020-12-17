package base.botapi;

import base.botapi.handlers.InputMessageHandler;
import base.botapi.states.BotState;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotContext {

    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    @Autowired
    public BotContext(List<InputMessageHandler> autowiredHandlers) {
        autowiredHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        if(isBuyingGym(currentState))
            return messageHandlers.get(BotState.BUY_GYM);
        return messageHandlers.get(currentState);
    }

    private boolean isBuyingGym(BotState currentState) {
        switch (currentState){
            case GYM_PAYMENT:
            case WAITING_FOR_PAYMENT:
                return true;
            default: return false;
        }
    }
}
