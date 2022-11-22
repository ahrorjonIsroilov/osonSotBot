package osonsot.mainbot.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.TextCommand;
import osonsot.base.handler.BaseHandler;
import osonsot.base.handler.Handler;
import osonsot.service.BotService;

import java.util.Optional;

@Component
public class MessageHandler extends BaseHandler implements Handler {
    public MessageHandler(BotService service) {
        super(service);
    }

    @Override
    @Async
    public void handle(Update update) {
        String text = update.getMessage().getText();
        TextCommand defaultCommand = textCommands.get(textCommands.size() - 1);
        if (update.getMessage().hasText()) {
            Optional<TextCommand> command =
                    textCommands.stream().filter(o -> o.getName(text).equals(text)).findFirst();
            if (command.isPresent()) {
                command.get().handleCommand(update);
            } else defaultCommand.handleCommand(update);
        } else if (update.getMessage().hasContact()) processors.get("contact").handleUpdate(update);
        else if (update.getMessage().hasPhoto()) processors.get("photo").handleUpdate(update);
    }
}
