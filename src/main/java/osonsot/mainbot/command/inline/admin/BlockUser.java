package osonsot.mainbot.command.inline.admin;

import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.mainbot.enums.Formatting;
import osonsot.service.BotService;

public class BlockUser extends CallbackCommand {
    public BlockUser(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "block-user";
    }

    @Override
    protected void handle(Update update) {
        Long userId = Long.parseLong(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1]);
        deleteMessage(update);
        sendMessage("Choose a blocking period", InlineButton.blockPeriod(userId), Formatting.BOLD);
    }

    @Override
    public String getName() {
        return null;
    }
}
