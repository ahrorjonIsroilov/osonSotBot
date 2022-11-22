package osonsot.mainbot.command.inline.admin;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.MarkupButton;
import osonsot.config.BotConfig;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.service.BotService;
import osonsot.util.Utils;

@Component
public class EditCategory extends CallbackCommand {
    public EditCategory(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "edit-cat";
    }

    @Override
    @Async
    protected void handle(Update update) {
        String id = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        Integer ID = Utils.isNumeric(id) ? Integer.parseInt(id) : null;
        deleteMessage(update);
        sendMessage("Enter new category name in uzbek", MarkupButton.cancel(lang), Formatting.BOLD);
        session.setState(State.EDIT_CATEGORY, chatId);
        session.setTempLong(ID != null ? ID.longValue() : null, chatId);
    }

    @Override
    public String getName() {
        return "Edit category";
    }
}
