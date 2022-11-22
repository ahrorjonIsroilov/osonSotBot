package osonsot.mainbot.command.inline.admin;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.entity.poster.Category;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.Language;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.util.List;

@Component
public class DeleteCategory extends CallbackCommand {
    public DeleteCategory(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "del-cat";
    }

    @Override
    @Async
    protected void handle(Update update) {
        String parentId = update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1];
        Long parent = Utils.isNumeric(parentId) ? Long.parseLong(parentId) : null;
        service.deleteCategory(parent);
        Integer returningCategoryId =
                session.getCategoryId(chatId) != null ? session.getCategoryId(chatId) : null;
        sendPopup("Deleted!", update.getCallbackQuery().getId());
        if (returningCategoryId != null) {
            Category category = service.getCategory(returningCategoryId.longValue());
            if (!service.existSubCategories(category.getId().intValue())) category.setIsFinal(true);
            service.saveCategory(category);
            session.setCategoryId(category.getParentId(), chatId);
            List<Category> categories = service.getSubCategories(returningCategoryId);
            editMessage(
                    update,
                    category.getName(session.getLang(chatId)),
                    InlineButton.categoryList(
                            categories, returningCategoryId, Language.ENGLISH, session.getByChatId(chatId)),
                    Formatting.BOLD);
        } else {
            List<Category> categories = service.getParentCategories();
            editMessage(
                    update,
                    "Categories",
                    InlineButton.categoryList(
                            categories, null, Language.ENGLISH, session.getByChatId(chatId)),
                    Formatting.BOLD);
        }
    }

    @Override
    public String getName() {
        return "Delete category";
    }
}
