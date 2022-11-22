package osonsot.mainbot.command.slash;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.TextCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.entity.auth.SessionElement;
import osonsot.entity.auth.SessionUser;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

@Component
public class Done extends TextCommand {

    public Done(BotService service) {
        super(service);
    }

    @Override
    @Async
    public void handle(Update update) {
        SessionUser sessionUser = this.session.getByChatId(chatId);
        switch (sessionUser.getRole()) {
            case USER -> {
                switch (sessionUser.getState()) {
                    case SUBMIT_PHOTO -> {
                        session.setState(State.WRITE_DESCRIPTION, chatId);
                        sendMessage(Words.WRITE_DESCRIPTION.lang(lang), Formatting.BOLD);
                    }
                    case EDIT_POSTER_PHOTO -> {
                        Poster p = session.getElement(chatId, SessionElement.POSTER);
                        p.getImages().clear();
                        p.setImages(session.getPhotos(chatId));
                        session.setElement(SessionElement.POSTER, p, chatId);
                        sendPhoto(
                                p,
                                Utils.getPosterInfoForOwner(p, lang),
                                InlineButton.edit(p, lang),
                                Formatting.BOLD);
                        session.setState(State.DEFAULT, chatId);
                    }
                    case DEFAULT -> {
                    }
                }
            }
            case ADMIN, OWNER -> {
            }
        }
    }

    @Override
    public String getName(String incoming_button) {
        return BotConfig.COMMAND_INIT + "done";
    }
}
