package osonsot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import osonsot.base.Bot;
import osonsot.command.Command;

@Configuration
public class BotExecutor {

    @Bean
    public void main() throws InterruptedException {
        TelegramBotsApi api;
        try {
            api = new TelegramBotsApi(DefaultBotSession.class);
            Bot bot = new Bot();
            Command.setBot(bot);
            api.registerBot(bot);
            System.out.println("connected");
        } catch (TelegramApiException e) {
            System.out.println("Not connected");
            System.out.println(e.getMessage());
            Thread.sleep(10000);
            main();
        }
    }
}
