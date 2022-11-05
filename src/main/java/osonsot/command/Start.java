package osonsot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.Command;

public class Start extends Command {
    private final String name = "/start";

    @Override
    public void handle(Update update) {
        sendMessage("Welcome");
    }

    @Override
    public String getName() {
        return name;
    }
}
