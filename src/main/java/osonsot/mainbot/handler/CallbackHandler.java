package osonsot.mainbot.handler;

import java.util.Optional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.base.handler.BaseHandler;
import osonsot.base.handler.Handler;
import osonsot.config.BotConfig;
import osonsot.service.BotService;

@Component
public class CallbackHandler extends BaseHandler implements Handler {

  public CallbackHandler(BotService service) {
    super(service);
  }

  @Override
  @Async
  public void handle(Update update) {
    Optional<CallbackCommand> command =
        callbackCommands.stream()
            .filter(
                o ->
                    update
                        .getCallbackQuery()
                        .getData()
                        .split(BotConfig.DATA_SEPARATOR)[0]
                        .equals(o.data()))
            .findFirst();
    command.ifPresent(value -> value.handleCommand(update));
  }
}
