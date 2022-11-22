package osonsot.mainbot.command.inline.user.poster;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.entity.auth.SessionElement;
import osonsot.entity.poster.Category;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Component
public class EditPoster extends CallbackCommand {
    public EditPoster(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "edit-poster";
    }

    @Override
    protected void handle(Update update) {
        Poster poster = session.getElement(chatId, SessionElement.POSTER) != null ?
                session.getElement(chatId, SessionElement.POSTER) : service.getPosterById(
                Long.parseLong(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[2]));
        String state = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        switch (state) {
            case "edit-pic" -> {
                deleteMessage(update);
                sendMessage(Words.EDIT_POSTER_PHOTO.lang(lang), Formatting.BOLD);
                session.setState(State.EDIT_POSTER_PHOTO, chatId);
                session.setElement(SessionElement.POSTER, poster, chatId);
            }
            case "edit-title" -> {
                deleteMessage(update);
                sendMessage(Words.EDIT_POSTER_NAME.lang(lang), Formatting.BOLD);
                session.setState(State.INPUT_NEW_POSTER_TITLE, chatId);
                session.setElement(SessionElement.POSTER, poster, chatId);
            }
            case "edit-description" -> {
                deleteMessage(update);
                sendMessage(Words.EDIT_POSTER_DESCRIPTION.lang(lang), Formatting.BOLD);
                session.setState(State.INPUT_NEW_POSTER_DESCRIPTION, chatId);
                session.setElement(SessionElement.POSTER, poster, chatId);
            }
            case "edit-price" -> {
                deleteMessage(update);
                sendMessage(Words.EDIT_PRICE.lang(lang), Formatting.BOLD);
                session.setState(State.INPUT_NEW_POSTER_PRICE, chatId);
                session.setElement(SessionElement.POSTER, poster, chatId);
            }
            case "edit-category" -> {
                List<Category> categories = service.getParentCategories();
                deleteMessage(update);
                sendMessage(Words.EDIT_CATEGORY.lang(lang),
                        InlineButton.categoryList(categories, null, lang, session.getByChatId(chatId)),
                        Formatting.BOLD);
                session.setState(State.SELECT_NEW_CATEGORY, chatId);
                session.setElement(SessionElement.POSTER, poster, chatId);
            }
            case "save-all" -> {
                Poster p = session.getElement(chatId, SessionElement.POSTER);
                if (p == null) {
                    sendPopup(Words.NOTHING_CHANGED.lang(lang), update.getCallbackQuery().getId(), true);
                    return;
                }
                sendPopup(Words.POST_UPDATED.lang(lang), update.getCallbackQuery().getId(), true);
                deleteMessage(update);
                sendMessage(ButtonTitle.HOME.get(lang), InlineButton.userMainDashboard(lang), Formatting.BOLD);
                service.deleteImageByPoster(p);
                p.setTelegraphUrl(Utils.editTelegraphPoster(p));
                service.savePoster(p);
                session.setPhotos(new ArrayList<>(), chatId);
                session.setElement(SessionElement.POSTER, null, chatId);
            }
        }
    }

    @Override
    public String getName() {
        return "Edit poster";
    }
}
