package osonsot.mainbot.command.inline.lang;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.mainbot.enums.Formatting;
import osonsot.service.BotService;

@Component
public class ChangeLang extends CallbackCommand {

  @Override
  public String data() {
    return "chLang";
  }

  public ChangeLang(BotService service) {
    super(service);
  }

  @Override
  protected void handle(Update update) {
    editMessage(
        update,
        "Тилни танланг\nSelect a language\nВыберите язык",
        InlineButton.chooseLang(session.getByChatId(chatId)),
        Formatting.BOLD);
  }

  @Override
  public String getName() {
    return "change language";
  }
}
