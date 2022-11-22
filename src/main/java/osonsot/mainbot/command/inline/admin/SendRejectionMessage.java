package osonsot.mainbot.command.inline.admin;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class SendRejectionMessage extends CallbackCommand {
    public SendRejectionMessage(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "reject";
    }

    @Override
    protected void handle(Update update) {
        String rejectionId = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        Language language = Language.DEFAULT.getByLangCode(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[2]);
        Poster poster =
                service.getPosterById(Long.parseLong(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[3]));
        String rejectionMessage = "";
        switch (rejectionId) {
            case "1" -> rejectionMessage = Words.REJECTION_1.lang(language);
            case "2" -> rejectionMessage = Words.REJECTION_2.lang(language);
            case "3" -> rejectionMessage = Words.REJECTION_3.lang(language);
            case "4" -> rejectionMessage = Words.REJECTION_4.lang(language);
            case "5" -> rejectionMessage = Words.REJECTION_5.lang(language);
            case "6" -> rejectionMessage = Words.REJECTION_6.lang(language);
            case "7" -> rejectionMessage = Words.REJECTION_7.lang(language);
            case "8" -> rejectionMessage = Words.REJECTION_8.lang(language);
        }
        sendPopup("Poster rejected", update.getCallbackQuery().getId());
        deleteMessage(update);
        sendMessage(
                rejectionMessage,
                poster.getOwner().getChatId(),
                InlineButton.seePoster(poster, session.getLang(poster.getOwner().getChatId())),
                Formatting.ITALIC);
    }

    @Override
    public String getName() {
        return "Send rejection message";
    }
}
