package osonsot.mainbot.command.inline.admin;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.poster.Category;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;
import osonsot.service.BotService;

import java.util.List;

@Component
public class Categories extends CallbackCommand {
    public Categories(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "categories";
    }

    @Override
    @Async
    protected void handle(Update update) {
        List<Category> categories = service.getParentCategories();
        session.setState(State.CATEGORIES, chatId);
        editMessage(
                update,
                "Categories",
                InlineButton.categoryList(categories, null, Language.ENGLISH, session.getByChatId(chatId)),
                Formatting.BOLD);
    }

    @Override
    public String getName() {
        return "Categories";
    }
}
