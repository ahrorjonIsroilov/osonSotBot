package osonsot.mainbot.command.inline.admin;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

import java.util.concurrent.TimeUnit;

@Component
public class RejectConfirmRecentPoster extends CallbackCommand {
    public RejectConfirmRecentPoster(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "recent";
    }

    @Override
    protected void handle(Update update) {
        String action = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        Long posterId =
                Long.parseLong(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[2]);
        Poster poster = service.getPosterById(posterId);
        switch (action) {
            case "confirm" -> {
                if (poster.getAccepted()) {
                    sendPopup(
                            "Poster already confirmed by %s".formatted(poster.getAcceptedBy()),
                            update.getCallbackQuery().getId());
                    return;
                }
                sendPopup("Poster confirmed ✅", update.getCallbackQuery().getId());
                deleteMessage(update);
                poster.setAccepted(true);
                poster.setRejected(false);
                poster.setAcceptedBy(service.getUserByChatId(chatId).getContact());
                sendMessage(
                        Words.POSTER_CONFIRMED_NOTIFICATION.lang(
                                session.getLang(poster.getOwner().getChatId())),
                        poster.getOwner().getChatId(),
                        InlineButton.seePoster(poster, poster.getOwner().getLanguage()),
                        Formatting.ITALIC);
            }
            case "reject" -> {
                poster.setRejected(true);
                editMedia(
                        update,
                        poster,
                        "Select a reason to reject this poster. This message will be sent to the poster owner",
                        InlineButton.rejectionCause(poster, poster.getOwner().getLanguage()));
                Runnable runnable = () -> {
                    poster.setRejected(false);
                    service.savePoster(poster);
                };
                scheduledThread.schedule(runnable, 15, TimeUnit.SECONDS);
            }
        }
        service.savePoster(poster);
    }

    @Override
    public String getName() {
        return "Reject confirm recent poster";
    }
}
