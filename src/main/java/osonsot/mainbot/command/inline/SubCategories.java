package osonsot.mainbot.command.inline;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.button.MarkupButton;
import osonsot.config.BotConfig;
import osonsot.entity.auth.SessionElement;
import osonsot.entity.poster.Category;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.util.List;

@Component
public class SubCategories extends CallbackCommand {
    public SubCategories(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "cat";
    }

    @Override
    @Async
    protected void handle(Update update) {
        String parentId = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        int parent = Utils.isNumeric(parentId) ? Integer.parseInt(parentId) : 0;
        Category category = service.getCategory((long) parent);
        switch (session.getRole(chatId)) {
            case ADMIN, OWNER -> {
                session.setState(State.CATEGORIES, chatId);
                session.setCategoryId(category.getParentId(), chatId);
                showSubcategories(update, lang, category);
            }
            case USER -> {
                switch (session.getState(chatId)) {
                    case CHOOSE_CATEGORY -> {
                        if (category.getIsFinal()) {
                            session.setCategoryId(category.getId().intValue(), chatId);
                            deleteMessage(update);
                            sendMessage(
                                    Words.SEND_POSTER_NAME.lang(lang), MarkupButton.cancel(lang), Formatting.BOLD);
                            session.setState(State.SUBMIT_POSTER_NAME, chatId);
                        } else showSubcategories(update, lang, category);
                    }
                    case SELECT_NEW_CATEGORY -> {
                        if (category.getIsFinal()) {
                            Poster poster = session.getElement(chatId, SessionElement.POSTER);
                            poster.setCategory(category);
                            deleteMessage(update);
                            sendPhoto(
                                    poster,
                                    Utils.getPosterInfoForOwner(poster, lang),
                                    InlineButton.edit(poster, lang),
                                    Formatting.BOLD);
                        } else showSubcategories(update, lang, category);
                    }
                }
            }
        }
    }

    private void showSubcategories(Update update, Language lang, Category category) {
        List<Category> categories = service.getSubCategories(category.getId().intValue());
        editMessage(
                update,
                !session.getRole(chatId).equals(Role.USER)
                        ? Words.CATEGORY_INFO.lang(lang).formatted(category.getName(lang), categories.size())
                        : Words.CHOOSE_CATEGORY.lang(lang),
                InlineButton.categoryList(
                        categories, category.getId().intValue(), lang, session.getByChatId(chatId)),
                Formatting.BOLD);
    }

    @Override
    public String getName() {
        return "Sub categories";
    }
}
