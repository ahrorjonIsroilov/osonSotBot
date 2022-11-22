package osonsot.mainbot.command.inline.user.setting;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class EditContact extends CallbackCommand {

  public EditContact(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "editContact";
  }

  @Override
  protected void handle(Update update) {
    editMessage(update, Words.EDIT_CONTACT.lang(lang), Formatting.BOLD);
    session.setState(State.EDIT_CONTACT, chatId);
  }

  private boolean validPhone(String phone) {
    try {
      Long.parseLong(phone);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private boolean validUzbPhone(String phone) {
    if (phone.startsWith("+")) phone = phone.substring(1);
    return phone.length() == 12 && phone.startsWith("998");
  }

  @Override
  public String getName() {
    return "edit contact";
  }
}
