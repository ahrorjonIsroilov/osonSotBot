package osonsot.mainbot.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.handler.BaseHandler;
import osonsot.base.handler.Handler;
import osonsot.service.BotService;

@Component
public class UpdateHandler extends BaseHandler implements Handler {

  private final MessageHandler messageHandler;
  private final CallbackHandler callbackHandler;

  public UpdateHandler(
      MessageHandler messageHandler, CallbackHandler callbackHandler, BotService service) {
    super(service);
    this.messageHandler = messageHandler;
    this.callbackHandler = callbackHandler;
  }

  @Override
  public void handle(Update update) {
    if (update.hasMessage()) messageHandler.handle(update);
    else if (update.hasCallbackQuery()) callbackHandler.handle(update);
    else if (update.hasInlineQuery()) {
      String query = update.getInlineQuery().getQuery();
    }
  }
}
