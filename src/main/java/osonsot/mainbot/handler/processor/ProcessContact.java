package osonsot.mainbot.handler.processor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import osonsot.base.handler.Processor;
import osonsot.button.InlineButton;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

import static osonsot.mainbot.enums.localization.Words.CHOOSE_REGION;
import static osonsot.mainbot.enums.localization.Words.CHOOSE_REGION_1;

@Component
public class ProcessContact extends Processor {
    public ProcessContact(BotService service) {
        super(service);
    }

    @Override
    public void handle(Update update) {
        Language lang = session.getLang(chatId);
        Contact contact = update.getMessage().getContact();
        String phone = getPhone(contact);
        if (validPhone(phone)) {
            session.setContact(phone, chatId);
            session.setState(State.CHOOSE_REGION, chatId);
            sendMessage(CHOOSE_REGION_1.lang(lang), new ReplyKeyboardRemove(true), Formatting.BOLD);
            sendMessage(CHOOSE_REGION.lang(lang), InlineButton.regions(), Formatting.BOLD);
        } else sendMessage(Words.ONLY_UZB_PHONE.lang(lang));
    }

    private boolean validPhone(String phone) {
        return phone.startsWith("998");
    }

    private String getPhone(Contact contact) {
        String number = contact.getPhoneNumber();
        if (number.startsWith("+")) number = number.substring(1);
        return number;
    }
}
