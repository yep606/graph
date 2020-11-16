package base.controller;

import base.botapi.UpdateProcessor;
import com.pengrad.telegrambot.BotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UpdateProcessor updateProcessor;

    @Autowired
    public Controller(UpdateProcessor updateProcessor) {
        this.updateProcessor = updateProcessor;
    }

    @PostMapping("/webhook")
    public void onUpdateReceived(@RequestBody String request) {
        updateProcessor.onUpdateReceived(BotUtils.parseUpdate(request));
    }


}
