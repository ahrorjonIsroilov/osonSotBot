package osonsot.mainbot.command.slash;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.TextCommand;
import osonsot.button.InlineButton;
import osonsot.config.BotConfig;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.service.BotService;

@Component
public class Start extends TextCommand {

    public Start(BotService service) {
        super(service);
    }

    @Override
    @Async
    public void handle(Update update) {
        if (session.existsByChatIdAndRegistered(chatId)) {
            session.setState(State.DEFAULT, chatId);
            switch (session.getRole(chatId)) {
                case USER -> sendMessage(
                        "Ассалому алайкум!",
                        InlineButton.userMainDashboard(session.getLang(chatId)),
                        Formatting.BOLD);
                case ADMIN, OWNER -> sendMessage(
                        "What's up!", InlineButton.adminMainDashboard(), Formatting.BOLD);
            }
        } else
            sendMessage(
                    "Тилни танланг\nSelect a language\nВыберите язык",
                    InlineButton.chooseLang(session.getByChatId(chatId)),
                    Formatting.BOLD);
    }

    @Override
    public String getName(String incoming_button) {
        return BotConfig.COMMAND_INIT + "start";
    }
}
