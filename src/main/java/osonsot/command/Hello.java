package osonsot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.Bot;
import osonsot.config.BotConfig;

public class Hello extends Command {
    private final String name = BotConfig.COMMAND_INIT + "hello";

    @Override
    public void handle(Update update) {
        sendMessage("Hello");
    }

    @Override
    public String getName() {
        return name;
    }
}
