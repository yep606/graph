package base.cache;

import base.botapi.states.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryCache implements DataCache {

    private Map<Long, BotState> usersBotStates = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(long userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(long userId) {
        BotState botState = usersBotStates.get(userId);
        return botState == null ? BotState.MAIN_MENU : botState;
    }
}
