package osonsot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class RequestController {

    @PostMapping("/telegram/getUpdate")
    public String getUpdate(@RequestBody Update update) {
        return "Hello world";
    }
}
