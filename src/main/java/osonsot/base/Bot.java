package osonsot.base;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.config.BotConfig;
import osonsot.mainbot.handler.UpdateHandler;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

  private final UpdateHandler handler;

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
}
