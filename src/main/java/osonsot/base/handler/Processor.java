package osonsot.base.handler;

import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.Command;
import osonsot.service.BotService;

public abstract class Processor extends Command {

    protected Processor(BotService service) {
        super(service);
    }

    public void handleUpdate(Update update) {
        beforeHandle(update);
        handle(update);
    }

    protected abstract void handle(Update update);
}
