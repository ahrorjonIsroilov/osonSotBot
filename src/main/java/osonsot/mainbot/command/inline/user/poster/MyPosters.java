package osonsot.mainbot.command.inline.user.poster;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.util.Objects;

@Component
public class MyPosters extends CallbackCommand {

    public MyPosters(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "my-ads";
    }

    @Override
    protected void handle(Update update) {
        String stage = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        Long currentPosterId =
                Long.parseLong(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[2]);
        switch (stage) {
            case "first" -> {
                session.setPageZero(chatId);
                Poster myPoster = service.getMyPoster(chatId, session.getPage(chatId));
                if (myPoster == null) {
                    sendPopup(Words.POSTERS_NOT_AVAILABLE.lang(lang), update.getCallbackQuery().getId());
                    return;
                }
                deleteMessage(update);
                sendPhoto(
                        myPoster,
                        Utils.getPosterInfoForOwner(myPoster, lang),
                        InlineButton.myPosters(myPoster, lang),
                        Formatting.BOLD);
            }
            case "next" -> {
                Poster test = service.getMyPoster(chatId, session.getPage(chatId) + 1);
                if (Objects.nonNull(test)) session.nextPage(chatId);
                else {
                    sendPopup(Words.LAST_PAGE.lang(lang), update.getCallbackQuery().getId());
                    return;
                }
                editMedia(
                        update,
                        test,
                        Utils.getPosterInfoForOwner(test, lang),
                        InlineButton.myPosters(test, lang));
            }
            case "prev" -> {
                if (session.getPage(chatId) > 0) session.previousPage(chatId);
                else {
                    sendPopup(Words.FIRST_PAGE.lang(lang), update.getCallbackQuery().getId());
                    return;
                }
                Poster myPoster = service.getMyPoster(chatId, session.getPage(chatId));
                editMedia(
                        update,
                        myPoster,
                        Utils.getPosterInfoForOwner(myPoster, lang),
                        InlineButton.myPosters(myPoster, lang));
            }
            case "edit", "cancel" -> {
                Poster currentPoster = service.getMyCurrentPoster(currentPosterId);
                editMessageCaption(
                        update,
                        Utils.getPosterInfoForOwner(currentPoster, lang),
                        InlineButton.myCurrentPoster(currentPoster, lang, false, false));
            }
            case "back-to-list" -> {
                Poster myPoster = service.getMyPoster(chatId, session.getPage(chatId));
                editMessageCaption(
                        update,
                        Utils.getPosterInfoForOwner(myPoster, lang),
                        InlineButton.myPosters(myPoster, lang));
            }
            case "mark-as-sold" -> {
                Poster currentPoster = service.getMyCurrentPoster(currentPosterId);
                if (!currentPoster.getSold())
                    editMessageCaption(
                            update,
                            Words.MARK_AS_SOLD.lang(lang),
                            InlineButton.myCurrentPoster(currentPoster, lang, true, false));
                else
                    sendPopup(Words.ALREADY_SOLD_POSTER.lang(lang), update.getCallbackQuery().getId(), true);
            }
            case "delete" -> {
                Poster currentPoster = service.getMyCurrentPoster(currentPosterId);
                editMessageCaption(
                        update,
                        Words.CONFIRM_DELETE_POSTER.lang(lang),
                        InlineButton.myCurrentPoster(currentPoster, lang, false, true));
            }
            case "confirm-delete" -> {
                service.deletePoster(currentPosterId);
                session.setPageZero(chatId);
                sendPopup(Words.POSTER_DELETED.lang(lang), update.getCallbackQuery().getId());
                Poster myPoster = service.getMyPoster(chatId, 0);
                if (myPoster == null) {
                    deleteMessage(update);
                    sendMessage(
                            ButtonTitle.HOME.get(lang), InlineButton.userMainDashboard(lang), Formatting.BOLD);
                    return;
                }
                editMedia(
                        update,
                        myPoster,
                        Utils.getPosterInfoForOwner(myPoster, lang),
                        InlineButton.myPosters(myPoster, lang));
            }
            case "confirm-sold" -> {
                Poster currentPoster = service.getMyCurrentPoster(currentPosterId);
                currentPoster.setSold(true);
                service.savePoster(currentPoster);
                editMessageCaption(
                        update,
                        Utils.getPosterInfoForOwner(currentPoster, lang),
                        InlineButton.myCurrentPoster(currentPoster, lang, false, false));
            }
            case "close" -> {
                deleteMessage(update);
                sendMessage(
                        ButtonTitle.HOME.get(lang), InlineButton.userMainDashboard(lang), Formatting.BOLD);
            }
        }
    }

    @Override
    public String getName() {
        return "My posters";
    }
}
