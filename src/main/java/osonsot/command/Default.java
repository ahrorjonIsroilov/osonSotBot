package osonsot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Default extends Command {

    @Override
    public void handle(Update update) {

    }

    @Override
    public String getName() {
        return "default";
    }
}
