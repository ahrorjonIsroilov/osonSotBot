package osonsot.mainbot.command.inline.user.poster;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.auth.SessionElement;
import osonsot.entity.auth.SessionUser;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PlacePoster extends CallbackCommand {

    public PlacePoster(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "placePoster";
    }

    @Override
    protected void handle(Update update) {
        Poster poster = session.getElement(chatId, SessionElement.POSTER);
        poster.setAddedDate(LocalDateTime.now());
        poster.setTelegraphUrl(Utils.createTelegraphPoster(poster));
        service.savePoster(poster);
        deleteMessage(update);
        sendMessage(Words.POSTER_PLACED.lang(lang), new ReplyKeyboardRemove(true), Formatting.BOLD);
        sendMessage(ButtonTitle.HOME.get(lang), InlineButton.userMainDashboard(lang), Formatting.BOLD);
        session.setElement(SessionElement.POSTER, null, chatId);
        SessionUser byChatId = session.getByChatId(chatId);
        byChatId.getPhotos().clear();
        session.setSession(chatId, Optional.of(byChatId));
    }

    @Override
    public String getName() {
        return "Place Poster";
    }
}
