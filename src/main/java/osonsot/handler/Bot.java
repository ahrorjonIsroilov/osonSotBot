package osonsot.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.BotConfig;
import osonsot.base.Command;
import osonsot.command.Start;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class Bot extends TelegramLongPollingBot {

    private Set<Command> commands;

    public Bot() {
        commands = new HashSet<>();
        commands.add(new Start());
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

//    @Override
//    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
//
//        return null;
//    }
//
//    @Override
//    public String getBotPath() {
//        return BotConfig.BOT_USERNAME;
//    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().hasText()) {
            Optional<Command> command = commands.stream().filter(o -> o.getName().equals(update.getMessage().getText())).findFirst();
            command.ifPresent(value -> value.handle(update));
        }
    }
}
