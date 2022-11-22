package osonsot.mainbot.command.inline.admin;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.util.List;

@Component
public class Recents extends CallbackCommand {
    public Recents(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "recents";
    }

    @Override
    @Async
    protected void handle(Update update) {
        List<Poster> unconfirmedPosters = service.getUnconfirmedPosters();
        if (unconfirmedPosters.isEmpty()) {
            sendPopup(Words.NO_RECENTS.lang(lang), update.getCallbackQuery().getId(), true);
            return;
        }
        sendUnconfirmedPosters(unconfirmedPosters);
    }

    private void sendUnconfirmedPosters(List<Poster> unconfirmedPosters) {
        Runnable runnable =
                () -> {
                    for (Poster poster : unconfirmedPosters) {
                        sendPhoto(
                                poster,
                                Utils.getPosterInfoForOwner(poster, lang),
                                InlineButton.confirmPoster(poster),
                                Formatting.BOLD);
                    }
                };
        thread.submit(runnable);
    }

    @Override
    public String getName() {
        return "Recent posters";
    }
}
