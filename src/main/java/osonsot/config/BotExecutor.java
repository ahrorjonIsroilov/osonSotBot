package osonsot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import osonsot.base.Bot;
import osonsot.base.ChatBot;
import osonsot.base.command.Command;
import osonsot.mainbot.handler.CallbackHandler;
import osonsot.mainbot.handler.MessageHandler;
import osonsot.mainbot.handler.UpdateHandler;
import osonsot.service.BotService;

@Configuration
public class BotExecutor {

    private final BotService service;

    public BotExecutor(BotService service) {
        this.service = service;
    }

    @Bean
    public void main() throws InterruptedException {
        TelegramBotsApi api;
        try {
            api = new TelegramBotsApi(DefaultBotSession.class);
            Bot bot =
                    new Bot(
                            new UpdateHandler(
                                    new MessageHandler(service), new CallbackHandler(service), service));
            ChatBot chatBot =
                    new ChatBot(
                            new UpdateHandler(
                                    new MessageHandler(service), new CallbackHandler(service), service));
            Command.setBot(bot);
            api.registerBot(bot);
            api.registerBot(chatBot);
            System.out.println("connected");
        } catch (TelegramApiException e) {
            System.out.println("Not connected");
            System.out.println(e.getMessage());
            Thread.sleep(10000);
            main();
        }
    }
}
