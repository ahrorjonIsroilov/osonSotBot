package osonsot.mainbot.command.inline.admin;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.config.BotConfig;
import osonsot.entity.auth.AuthUser;
import osonsot.mainbot.enums.Emoji;
import osonsot.service.BotService;

import java.util.Objects;

@Component
public class UserInfo extends CallbackCommand {
    public UserInfo(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "user";
    }

    @Override
    protected void handle(Update update) {
        Long userId =
                Long.parseLong(update.getCallbackQuery().getData().split(BotConfig.DATA_SEPARATOR)[1]);
        AuthUser user = service.getUserById(userId);
        if (Objects.isNull(user)) {
            sendPopup(Emoji.INFO.get() + " Nobody found with this ID", update.getCallbackQuery().getId(), true);
            return;
        }
        sendPopup(prepareUserMessage(user), update.getCallbackQuery().getId(), true);
    }

    private String prepareUserMessage(AuthUser user) {
        return "📞 Phone: " + user.getContact() + "\n" +
                "📍 Location: " + user.getLocation().getRegion().getRegion() + ", " + user.getLocation().getName() + "\n" +
                "🌐 Language: " + user.getLanguage() + "\n" +
                "👁‍🗨 Status: " + user.getBlocked() + "\n" +
                "0️⃣ Blocked count: " + user.getBlockedCount();
    }

    @Override
    public String getName() {
        return "User info";
    }
}
