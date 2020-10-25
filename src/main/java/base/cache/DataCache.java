package base.cache;

import base.botapi.states.BotState;
import base.domain.Task;

public interface DataCache {

    void setUsersCurrentBotState(long userId, BotState botState);

    BotState getUsersCurrentBotState(long userId);

    void setUserTask(long userId, Task task);

    Task getUsersTask(long userId);


}
