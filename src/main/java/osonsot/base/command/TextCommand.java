package osonsot.base.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.service.BotService;

public abstract class TextCommand extends Command {

  protected TextCommand(BotService service) {
    super(service);
  }

  public void handleCommand(Update update) {
    if (beforeHandle(update)) handle(update);
  }

  protected abstract void handle(Update update);

  public abstract String getName(String buttonTitle);
}
