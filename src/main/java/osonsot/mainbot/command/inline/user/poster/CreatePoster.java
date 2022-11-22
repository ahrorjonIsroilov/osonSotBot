package osonsot.mainbot.command.inline.user.poster;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.poster.Category;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

import java.util.List;

@Component
public class CreatePoster extends CallbackCommand {

    public CreatePoster(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "postAd";
    }

    @Override
    protected void handle(Update update) {
        List<Category> categories = service.getParentCategories();
        session.setState(State.CHOOSE_CATEGORY, chatId);
        editMessage(
                update,
                Words.CHOOSE_CATEGORY.lang(lang),
                InlineButton.categoryList(categories, null, lang, session.getByChatId(chatId)),
                Formatting.BOLD);
    }

    @Override
    public String getName() {
        return "Create poster";
    }
}
