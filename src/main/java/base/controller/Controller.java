package base.controller;

import base.bot.api.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UpdateHandler updateHandler;

    @Autowired
    public Controller(UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

    @PostMapping("/")
    public void onUpdateReceived(@RequestBody String request){
        updateHandler.handleUpdate(request);
    }


}
