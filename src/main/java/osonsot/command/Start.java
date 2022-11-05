package osonsot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.Bot;
import osonsot.config.BotConfig;

public class Start extends Command {
    private final String name = BotConfig.COMMAND_INIT + "start";

    @Override
    public void handle(Update update) {
        sendMessage("Welcome");
    }

    @Override
    public String getName() {
        return name;
    }
}
