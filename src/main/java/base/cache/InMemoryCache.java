package base.cache;

import base.botapi.states.BotState;
import base.domain.Task;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryCache implements DataCache {

    private Map<Long, BotState> usersBotStates = new HashMap<>();
    private Map<Long, Task> usersTask = new HashMap<>();

    @Override
    public void setUsersCurrentBotState(long userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    @Override
    public BotState getUsersCurrentBotState(long userId) {
        BotState botState = usersBotStates.get(userId);
        return botState == null ? BotState.MAIN_MENU : botState;
    }

    @Override
    public void setUserTask(long userId, Task task) {
        usersTask.put(userId, task);
    }

    @Override
    public Task getUsersTask(long userId) {
        Task task = usersTask.get(userId);
        return task == null ? new Task() : task;
    }
}
