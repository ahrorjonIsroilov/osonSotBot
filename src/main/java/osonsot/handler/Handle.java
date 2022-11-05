package osonsot.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface Handle {

    default void customHandle(Update update) {
        checkUser(update);
    }

    default void checkUser(Update update) {
    }
}
