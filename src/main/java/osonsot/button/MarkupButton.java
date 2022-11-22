package osonsot.button;

import static osonsot.mainbot.enums.localization.ButtonTitle.SHARE_CONTACT;

import java.util.Collections;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.mainbot.enums.localization.Language;

@Component
public class MarkupButton {
  private static final ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();

  public static ReplyKeyboardMarkup phoneButton(Language lang) {
    KeyboardButton phoneContact = new KeyboardButton(SHARE_CONTACT.get(lang));
    phoneContact.setRequestContact(true);
    KeyboardRow row1 = new KeyboardRow();
    row1.add(phoneContact);
    board.setKeyboard(Collections.singletonList(row1));
    board.setResizeKeyboard(true);
    board.setSelective(true);
    return board;
  }

  public static ReplyKeyboardMarkup cancel(Language lang) {
    KeyboardButton cancel = new KeyboardButton(ButtonTitle.CANCEL.get(lang));
    KeyboardRow row1 = new KeyboardRow();
    row1.add(cancel);
    board.setKeyboard(Collections.singletonList(row1));
    board.setResizeKeyboard(true);
    board.setSelective(true);
    return board;
  }
}
