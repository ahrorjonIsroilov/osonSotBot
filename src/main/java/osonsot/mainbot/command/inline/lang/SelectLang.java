package osonsot.mainbot.command.inline.lang;

import static osonsot.config.BotConfig.DATA_SEPARATOR;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.button.MarkupButton;
import osonsot.entity.auth.AuthUser;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class SelectLang extends CallbackCommand {

  @Override
  public String data() {
    return "lang";
  }

  public SelectLang(BotService service) {
    super(service);
  }

  @Override
  protected void handle(Update update) {
    String langCode = update.getCallbackQuery().getData().split(DATA_SEPARATOR)[1];
    if (!service.existsByChatId(chatId)) {
      session.setLang(Language.DEFAULT.getByLangCode(langCode), chatId);
      session.setState(State.SHARE_CONTACT, chatId);
      deleteMessage(update);
      sendMessage(
          Words.SHARE_CONTACT.lang(session.getLang(chatId)),
          MarkupButton.phoneButton(session.getLang(chatId)),
          Formatting.BOLD);
    } else {
      AuthUser user = service.getUserByChatId(chatId);
      user.setLanguage(Language.DEFAULT.getByLangCode(langCode));
      service.saveUser(user);
      session.setLang(Language.DEFAULT.getByLangCode(langCode), chatId);
      Language lang = session.getLang(chatId);
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
      sendPopup(Words.LANG_CHANGED.lang(lang), update.getCallbackQuery().getId());
    }
  }

  @Override
  public String getName() {
    return "Select language";
  }
}
