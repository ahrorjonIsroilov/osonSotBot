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
public class AddCategory extends CallbackCommand {
    public AddCategory(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "addCategory";
    }

    @Override
    @Async
    protected void handle(Update update) {
        String parentId = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        Integer parent = Utils.isNumeric(parentId) ? Integer.parseInt(parentId) : null;
        deleteMessage(update);
        sendMessage(
                "Enter category name in uzbek. For example <i>'Maishiy texnika'</i>",
                MarkupButton.cancel(lang),
                Formatting.BOLD);
        session.setState(State.ADD_CATEGORY, chatId);
        session.setTempLong(parent != null ? parent.longValue() : null, chatId);
    }

    @Override
    public String getName() {
        return "Add category";
    }
}
