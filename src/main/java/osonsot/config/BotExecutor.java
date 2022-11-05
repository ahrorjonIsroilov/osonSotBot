package osonsot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import osonsot.BotConfig;
import osonsot.handler.Bot;

@Configuration
public class BotExecutor {
    @Bean
    public void run() throws InterruptedException {
        try {
            createTelegramApi();
            System.out.println("Connected to telegram");
        } catch (TelegramApiException e) {
            System.out.println("Network problems");
            e.printStackTrace();
            Thread.sleep(150000);
            run();
        }
    }

    private void createTelegramApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi;
        if (BotConfig.USE_WEBHOOK) {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot()/*, new SetWebhook("https://66f6-89-146-124-119.eu.ngrok.io")*/);
        }
    }

    @Bean
    SetWebhook setWebhook() {
        return new SetWebhook();
    }
}
