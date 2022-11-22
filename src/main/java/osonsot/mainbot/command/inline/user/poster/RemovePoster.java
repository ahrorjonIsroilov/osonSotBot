package osonsot.mainbot.command.inline.user.poster;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.auth.SessionElement;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class RemovePoster extends CallbackCommand {

  public RemovePoster(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "deletePoster";
  }

  @Override
  protected void handle(Update update) {
    session.setElement(SessionElement.POSTER, null, chatId);
    sendPopup(Words.POSTER_REMOVED.lang(lang), update.getCallbackQuery().getId());
    deleteMessage(update);
    sendMessage(ButtonTitle.HOME.get(lang), InlineButton.userMainDashboard(lang), Formatting.BOLD);
  }

  @Override
  public String getName() {
    return "Confirm Poster";
  }
}
