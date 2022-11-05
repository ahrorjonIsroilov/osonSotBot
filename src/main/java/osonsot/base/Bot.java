package osonsot.base;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.command.Command;
import osonsot.command.Hello;
import osonsot.command.Start;
import osonsot.config.BotConfig;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class Bot extends TelegramLongPollingBot {

    public Bot() {
        commands = new HashSet<>();
        commands.add(new Start());
        commands.add(new Hello());
    }
    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    private final Set<Command> commands;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().hasText()) {
            Optional<Command> command = commands.stream().filter(o -> o.getName().equals(update.getMessage().getText())).findFirst();
            command.ifPresent(value -> value.handleCommand(update));
        }
    }

}
