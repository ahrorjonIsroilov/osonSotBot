package osonsot.mainbot.command.inline.user.setting;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.auth.AuthUser;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class ControlNotification extends CallbackCommand {

  public ControlNotification(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "notification";
  }

  @Override
  protected void handle(Update update) {
    AuthUser user = service.getUserByChatId(chatId);
    user.setNotification(!user.getNotification());
    service.saveUser(user);
    sendPopup(
        user.getNotification()
            ? Words.NOTIFICATION_ON.lang(lang)
            : Words.NOTIFICATION_OFF.lang(lang),
        update.getCallbackQuery().getId());
    editMessage(
        update,
        Words.USER_INFO
            .lang(lang)
            .formatted(
                user.getContact(),
                user.getExtraContact() != null
                    ? user.getExtraContact()
                    : Words.UNAVAILABLE.lang(lang),
                """
                                        %s:%s, %s:%s"""
                    .formatted(
                        Words.REGION.lang(lang),
                        user.getLocation().getRegion().getRegion(),
                        user.getLocation().equals(District.TASHKENT_CITY)
                            ? Words.CITY.lang(lang)
                            : Words.DISTRICT.lang(lang),
                        user.getLocation().getName())),
        InlineButton.userSettings(user),
        Formatting.BOLD);
  }

  @Override
  public String getName() {
    return "Control notification";
  }
}
