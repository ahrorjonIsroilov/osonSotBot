package osonsot.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.config.BotConfig;
import osonsot.handler.UpdateHandler;


@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    public UpdateHandler handler;
    private static final Bot instance = new Bot();

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        handler.handle(update);
    }

    public static Bot getInstance() {
        if (instance != null) return instance;
        else return new Bot();
    }
}
