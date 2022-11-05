package osonsot.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.command.Command;
import osonsot.command.Start;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UpdateHandler {

    private final Set<Command> commands;

    public UpdateHandler() {
        commands = new HashSet<>();
        commands.add(new Start());
    }

    public void handle(Update update) {
        if (update.getMessage().hasText()) {
            Optional<Command> command = commands.stream().filter(o -> o.getName().equals(update.getMessage().getText())).findFirst();
            command.ifPresent(value -> value.handleCommand(update));
        }
    }
}
