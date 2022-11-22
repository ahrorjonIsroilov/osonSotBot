package osonsot.mainbot.command.inline;

import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.auth.AuthUser;
import osonsot.entity.poster.Category;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class Back extends CallbackCommand {

  public Back(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "back";
  }

  @Override
  protected void handle(Update update) {
    switch (session.getState(chatId)) {
      case CATEGORIES -> {
        Integer returningCategoryId =
            session.getCategoryId(chatId) != null ? session.getCategoryId(chatId) : null;
        if (returningCategoryId != null) {
          Category category = service.getCategory(returningCategoryId.longValue());
          session.setCategoryId(category.getParentId(), chatId);
          List<Category> categories = service.getSubCategories(returningCategoryId);
          editMessage(
              update,
              Words.CATEGORY_INFO
                  .lang(Language.ENGLISH)
                  .formatted(category.getName(Language.ENGLISH), categories.size()),
              InlineButton.categoryList(
                  categories, returningCategoryId, Language.ENGLISH, session.getByChatId(chatId)),
              Formatting.BOLD);
        } else {
          List<Category> categories = service.getParentCategories();
          editMessage(
              update,
              "Categories",
              InlineButton.categoryList(
                  categories, null, Language.ENGLISH, session.getByChatId(chatId)),
              Formatting.BOLD);
        }
      }
      case SETTINGS -> {
        editMessage(
            update,
            Words.MAIN_MENU.lang(lang),
            InlineButton.userMainDashboard(lang),
            Formatting.BOLD);
        session.setState(State.DEFAULT, chatId);
      }
      case EDIT_CONTACT_PANEL -> {
        AuthUser user = service.getUserByChatId(chatId);
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
        session.setState(State.SETTINGS, chatId);
      }
    }
  }

  @Override
  public String getName() {
    return "back";
  }
}
