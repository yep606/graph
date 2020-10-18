package base.cache;

import base.botapi.states.BotState;

public interface DataCache {

    void setUsersCurrentBotState(long userId, BotState botState);

    BotState getUsersCurrentBotState(long userId);


}
