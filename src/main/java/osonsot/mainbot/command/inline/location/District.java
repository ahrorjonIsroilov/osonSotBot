package osonsot.mainbot.command.inline.location;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.entity.auth.AuthUser;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static osonsot.config.BotConfig.DATA_SEPARATOR;
import static osonsot.mainbot.enums.localization.Words.REGISTERED;

@Component
public class District extends CallbackCommand {

    public District(BotService service) {
        super(service);
    }

    @Override
    public String data() {
        return "dis";
    }

    @Override
    protected void handle(Update update) {
        String data = update.getCallbackQuery().getData().split(DATA_SEPARATOR)[1];
        osonsot.mainbot.enums.District location = getDistrictFromData(data);
        String username = "NONE";
        if (update.getCallbackQuery().getFrom().getUserName() != null)
            username = update.getCallbackQuery().getFrom().getUserName();
        if (!service.existsByChatId(chatId)) {
            service.saveUser(
                    AuthUser.builder()
                            .language(lang)
                            .chatId(chatId)
                            .contact(session.getContact(chatId))
                            .page(0)
                            .username(username)
                            .role(Role.USER)
                            .location(location)
                            .score(0)
                            .state(State.DEFAULT)
                            .notification(true)
                            .registered(true)
                            .blocked(false)
                            .blockedCount(0)
                            .blockedUntil(LocalDateTime.now().minus(1, ChronoUnit.DAYS))
                            .build());
            deleteMessage(update);
            sendSticker(
                    "CAACAgIAAxkBAAEBcMljaWS2xNt46bXxm7NJcn6bc7A-5wAC1hgAAt_skUmRnB_mBcJtuisE",
                    new ReplyKeyboardRemove(true));
            sendMessage(REGISTERED.lang(lang), InlineButton.userMainDashboard(lang), Formatting.BOLD);
            session.setLocation(location, chatId);
        } else {
            AuthUser user = service.getUserByChatId(chatId);
            user.setLocation(location);
            service.saveUser(user);
            editMessage(
                    update,
                    Words.USER_INFO
                            .lang(lang)
                            .formatted(
                                    user.getContact(),
                                    user.getExtraContact() != null
                                            ? user.getExtraContact()
                                            : Words.UNAVAILABLE.lang(lang),
                                    """
                                            %s:%s, %s:%s"""
                                            .formatted(
                                                    Words.REGION.lang(lang),
                                                    user.getLocation().getRegion().getRegion(),
                                                    user.getLocation().equals(osonsot.mainbot.enums.District.TASHKENT_CITY)
                                                            ? Words.CITY.lang(lang)
                                                            : Words.DISTRICT.lang(lang),
                                                    user.getLocation().getName())),
                    InlineButton.userSettings(user),
                    Formatting.BOLD);
            sendPopup(Words.LOCATION_CHANGED.lang(lang), update.getCallbackQuery().getId());
        }
    }

    private osonsot.mainbot.enums.District getDistrictFromData(String data) {
        return Arrays.stream(osonsot.mainbot.enums.District.values())
                .filter(val -> val.getDistrict().equals(data))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getName() {
        return "district";
    }
}
