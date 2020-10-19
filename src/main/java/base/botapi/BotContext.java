package base.botapi;

import base.botapi.handlers.InputMessageHandler;
import base.botapi.states.BotState;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotContext {

    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotContext(List<InputMessageHandler> autowiredHandlers) {
        autowiredHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        if(isFillingLab(currentState))
            return messageHandlers.get(BotState.ASK_LABS);
        return messageHandlers.get(currentState);
    }

    private boolean isFillingLab(BotState currentState) {
        switch (currentState){
            case ASK_DESCRIPTION:
            case ASK_EXPIRATION:
            case ASK_LABS:
            case LAB_FILLED:
                return true;
            default: return false;
        }
    }
}
