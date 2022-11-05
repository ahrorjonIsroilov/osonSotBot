package osonsot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import osonsot.base.Bot;


public abstract class Command {
    private Long chatId;

    private static Bot botStatic;

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
            botStatic.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setBot(Bot bot) {
        botStatic = bot;
    }
}
