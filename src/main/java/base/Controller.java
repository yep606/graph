package base;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/")
    public boolean onUpdateReceived(@RequestBody String request){

        System.out.println(request);

        Update update = BotUtils.parseUpdate(request);
        Message message = update.message();

        System.out.println("After parsing -> " + message.chat().firstName());

        return true;

    }


}
