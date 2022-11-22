package osonsot.mainbot.command.inline.user.setting;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class EditContactPanel extends CallbackCommand {

  public EditContactPanel(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "eContact";
  }

  @Override
  protected void handle(Update update) {
    String phone = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
    editMessage(
        update,
        Words.EDIT_CONTACT.lang(lang),
        InlineButton.editContact(phone, lang),
        Formatting.BOLD);
    session.setState(State.EDIT_CONTACT_PANEL, chatId);
  }

  @Override
  public String getName() {
    return "edit contact";
  }
}
