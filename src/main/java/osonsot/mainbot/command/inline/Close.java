package osonsot.mainbot.command.inline;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.service.BotService;

@Component
public class Close extends CallbackCommand {
  public Close(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "close";
  }

  @Override
  protected void handle(Update update) {
    deleteMessage(update);
  }

  @Override
  public String getName() {
    return null;
  }
}
