package osonsot.mainbot.command.text;

import java.util.HashMap;
import java.util.Optional;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import osonsot.base.command.TextCommand;
import osonsot.button.InlineButton;
import osonsot.entity.auth.SessionUser;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

public class Cancel extends TextCommand {
  public Cancel(BotService service) {
    super(service);
  }

  @Override
  protected void handle(Update update) {
    SessionUser byChatId = session.getByChatId(chatId);
    byChatId.setState(State.DEFAULT);
    byChatId.getPhotos().clear();
    byChatId.setElements(new HashMap<>());
    session.setSession(chatId, Optional.of(byChatId));
    sendMessage(Words.CANCELLATION.lang(lang), new ReplyKeyboardRemove(true), Formatting.ITALIC);
    sendMessage(
        ButtonTitle.HOME.get(lang),
        session.getRole(chatId).equals(Role.USER)
            ? InlineButton.userMainDashboard(lang)
            : InlineButton.adminMainDashboard(),
        Formatting.BOLD);
  }

  @Override
  public String getName(String incoming_button) {
    return ButtonTitle.CANCEL.getDefault(incoming_button);
  }
}
