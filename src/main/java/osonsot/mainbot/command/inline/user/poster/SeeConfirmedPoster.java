package osonsot.mainbot.command.inline.user.poster;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.service.BotService;
import osonsot.util.Utils;

@Component
public class SeeConfirmedPoster extends CallbackCommand {
    public SeeConfirmedPoster(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "see-poster";
    }

    @Override
    protected void handle(Update update) {
        Long posterId =
                Long.parseLong(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1]);
        Poster poster = service.getPosterById(posterId);
        deleteMessage(update);
        sendPhoto(
                poster,
                Utils.getPosterInfoForOwner(poster, lang),
                poster.getAccepted() ? InlineButton.close(session.getLang(poster.getOwner().getChatId())) :
                        InlineButton.edit(poster, lang),
                Formatting.BOLD);
    }

    @Override
    public String getName() {
        return "See confirmed poster";
    }
}
