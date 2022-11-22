package osonsot.mainbot.command.inline;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.service.BotService;

@Component
public class Home extends CallbackCommand {

  public Home(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "home";
  }

  @Override
  protected void handle(Update update) {
    Role role = session.getRole(chatId);
    session.setState(State.DEFAULT, chatId);
    editMessage(
        update,
        ButtonTitle.HOME.get(lang),
        !role.equals(Role.USER)
            ? InlineButton.adminMainDashboard()
            : InlineButton.userMainDashboard(lang),
        Formatting.BOLD);
  }

  @Override
  public String getName() {
    return "home";
  }
}
