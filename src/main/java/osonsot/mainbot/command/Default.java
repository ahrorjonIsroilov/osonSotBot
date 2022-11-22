package osonsot.mainbot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import osonsot.base.command.TextCommand;
import osonsot.button.InlineButton;
import osonsot.entity.auth.AuthUser;
import osonsot.entity.auth.SessionElement;
import osonsot.entity.poster.Category;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Default extends TextCommand {
    public Default(BotService service) {
        super(service);
    }

    @Override
    protected void handle(Update update) {
        Long parentId = session.getTempLong(chatId);
        String name = update.getMessage().getText();
        switch (session.getState(chatId)) {
            case SUBMIT_POSTER_NAME -> {
                String title = update.getMessage().getText();
                if (title.length() > 80) {
                    sendMessage(Words.MAX_LENGTH_REACHED.lang(lang), Formatting.BOLD);
                    return;
                }
                session.setElement(SessionElement.POSTER_TITLE, title, chatId);
                sendMessage(Words.SEND_POSTER_PHOTO.lang(lang), Formatting.BOLD);
                session.setState(State.SUBMIT_PHOTO, chatId);
            }
            case WRITE_DESCRIPTION -> {
                String description = update.getMessage().getText();
                if (description.length() > 500) {
                    sendMessage(Words.MAX_LENGTH_REACHED.lang(lang), Formatting.BOLD);
                    return;
                }
                session.setElement(SessionElement.POSTER_DESCRIPTION, description, chatId);
                sendMessage(Words.SEND_PRICE.lang(lang), Formatting.BOLD);
                session.setState(State.SEND_PRICE, chatId);
            }
            case SEND_PRICE -> {
                String updateText = update.getMessage().getText();
                if (!Utils.isNumeric(updateText)) {
                    sendMessage(Words.INVALID_PRICE.lang(lang), Formatting.BOLD);
                    return;
                }
                AuthUser owner = service.getUserByChatId(chatId);
                Long price = Long.parseLong(updateText);
                Poster poster =
                        Poster.builder()
                                .price(price)
                                .title(session.getElement(chatId, SessionElement.POSTER_TITLE))
                                .accepted(false)
                                .images(session.getPhotos(chatId))
                                .sold(false)
                                .rejected(false)
                                .viewCount(0L)
                                .location(owner.getLocation())
                                .description(session.getElement(chatId, SessionElement.POSTER_DESCRIPTION))
                                .category(service.getCategory(session.getCategoryId(chatId).longValue()))
                                .addedDate(LocalDateTime.now())
                                .owner(owner)
                                .build();
                sendPhoto(
                        poster,
                        Utils.getPosterInfo(poster, lang),
                        InlineButton.placePoster(session.getLang(chatId)),
                        Formatting.BOLD);
                session.setElement(SessionElement.POSTER, poster, chatId);
            }
            case ADD_CATEGORY -> {
                if (Utils.invalidCategoryName(name)) {
                    sendMessage("Invalid category name!", Formatting.BOLD);
                    return;
                }
                Runnable runnable =
                        () -> {
                            if (parentId != null) {
                                Category category = service.getCategory(parentId);
                                category.setIsFinal(false);
                                service.saveCategory(category);
                            }
                            Category category =
                                    Category.builder()
                                            .isSubcategory(session.getTempLong(chatId) != null)
                                            .parentId(
                                                    session.getTempLong(chatId) != null
                                                            ? Math.toIntExact(session.getTempLong(chatId))
                                                            : null)
                                            .nameUz(name)
                                            .nameRu(Utils.getTranslation(name, Language.RUSSIAN))
                                            .nameEn(Utils.getTranslation(name, Language.ENGLISH))
                                            .isFinal(true)
                                            .build();
                            service.saveCategory(category);
                            session.setState(State.CATEGORIES, chatId);
                            Integer returningCategoryId =
                                    session.getTempLong(chatId) != null
                                            ? session.getTempLong(chatId).intValue()
                                            : null;
                            backToCategory(returningCategoryId);
                        };
                thread.submit(runnable);
            }
            case EDIT_CATEGORY -> {
                if (Utils.invalidCategoryName(name)) {
                    sendMessage("Invalid category name!", Formatting.BOLD);
                    return;
                }
                Category category = service.getCategory(parentId);
                Runnable runnable =
                        () -> {
                            category.setNameUz(name);
                            category.setNameEn(Utils.getTranslation(name, Language.ENGLISH));
                            category.setNameRu(Utils.getTranslation(name, Language.RUSSIAN));
                            service.saveCategory(category);
                            List<Category> subCategories = service.getSubCategories(Math.toIntExact(parentId));
                            sendMessage("Edited!", new ReplyKeyboardRemove(true), Formatting.BOLD);
                            session.setState(State.CATEGORIES, chatId);
                            sendMessage(
                                    category.getName(lang),
                                    InlineButton.categoryList(
                                            subCategories, parentId.intValue(), lang, session.getByChatId(chatId)),
                                    Formatting.BOLD);
                        };
                thread.submit(runnable);
            }
            case INPUT_NEW_POSTER_TITLE -> {
                Poster poster = session.getElement(chatId, SessionElement.POSTER);
                String title = update.getMessage().getText();
                if (title.length() > 80) {
                    sendMessage(Words.MAX_LENGTH_REACHED.lang(lang), Formatting.BOLD);
                    return;
                }
                poster.setTitle(title);
                sendPhoto(poster,
                        Utils.getPosterInfoForOwner(poster, lang),
                        InlineButton.edit(poster, lang),
                        Formatting.BOLD);
                session.setElement(SessionElement.POSTER, poster, chatId);
                session.setState(State.DEFAULT, chatId);
            }
            case INPUT_NEW_POSTER_DESCRIPTION -> {
                Poster poster = session.getElement(chatId, SessionElement.POSTER);
                String description = update.getMessage().getText();
                if (description.length() > 500) {
                    sendMessage(Words.MAX_LENGTH_REACHED.lang(lang), Formatting.BOLD);
                    return;
                }
                poster.setDescription(description);
                sendPhoto(poster,
                        Utils.getPosterInfoForOwner(poster, lang),
                        InlineButton.edit(poster, lang),
                        Formatting.BOLD);
                session.setElement(SessionElement.POSTER, poster, chatId);
                session.setState(State.DEFAULT, chatId);
            }
            case INPUT_NEW_POSTER_PRICE -> {
                Poster poster = session.getElement(chatId, SessionElement.POSTER);
                String updateText = update.getMessage().getText();
                if (!Utils.isNumeric(updateText)) {
                    sendMessage(Words.INVALID_PRICE.lang(lang), Formatting.BOLD);
                    return;
                }
                poster.setPrice(Long.parseLong(updateText));
                sendPhoto(poster,
                        Utils.getPosterInfoForOwner(poster, lang),
                        InlineButton.edit(poster, lang),
                        Formatting.BOLD);
                session.setElement(SessionElement.POSTER, poster, chatId);
                session.setState(State.DEFAULT, chatId);
            }

            case DEFAULT -> {
            }
        }
    }

    private void backToCategory(Integer returningCategoryId) {
        if (returningCategoryId != null) {
            Category category = service.getCategory(returningCategoryId.longValue());
            session.setCategoryId(category.getParentId(), chatId);
            List<Category> categories = service.getSubCategories(returningCategoryId);
            sendMessage(
                    category.getName(session.getLang(chatId)),
                    InlineButton.categoryList(
                            categories, returningCategoryId, Language.ENGLISH, session.getByChatId(chatId)),
                    Formatting.BOLD);
        } else {
            List<Category> categories = service.getParentCategories();
            sendMessage(
                    "Categories",
                    InlineButton.categoryList(
                            categories, null, Language.ENGLISH, session.getByChatId(chatId)),
                    Formatting.BOLD);
        }
    }

    @Override
    public String getName(String incoming_button) {
        return "default";
    }
}
