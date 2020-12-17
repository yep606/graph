package base.botapi.handlers;

import base.botapi.states.BotState;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;

public interface InputMessageHandler {

    BotState getHandlerName();

    SendMessage handle(Message message);


}
