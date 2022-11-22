package osonsot.mainbot.command.inline.user.setting;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class AddContact extends CallbackCommand {

  public AddContact(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "aContact";
  }

  @Override
  protected void handle(Update update) {
    sendPopup(Words.ADD_CONTACT_ALERT.lang(lang), update.getCallbackQuery().getId(), true);
  }

  @Override
  public String getName() {
    return "add contact";
  }
}
