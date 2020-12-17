package base.controller;

import base.botapi.UpdateProcessor;
import base.service.RestService;
import com.pengrad.telegrambot.BotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    public RestService restService;

    @PostMapping("/")
    public void onUpdateReceived(@RequestBody String request) {
        System.out.println(request);
        System.out.println("dasdasda");
        updateProcessor.onUpdateReceived(BotUtils.parseUpdate(request));
    }

    @GetMapping("/test")
    public void das(){
        System.out.println("get response");

    }


}
