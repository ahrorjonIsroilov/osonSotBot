package osonsot.base;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import osonsot.handler.Bot;

@Component
public abstract class Command {


    private Bot bot;
    private Long chatId;

    public abstract void handle(Update update);

    public abstract String getName();

    public void handleCommand(Update update) {
        beforeHandle(update);
        handle(update);
        afterHandle();
    }

    private void beforeHandle(Update update) {
        chatId = update.getMessage().getChatId();
    }

    private void afterHandle() {

    }

    public void sendMessage(String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
