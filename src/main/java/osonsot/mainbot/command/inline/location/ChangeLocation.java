package osonsot.mainbot.command.inline.location;

import java.util.Arrays;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;

@Component
public class ChangeLocation extends CallbackCommand {

  public ChangeLocation(BotService service) {
    super(service);
  }

  @Override
  public String data() {
    return "chLocation";
  }

  @Override
  protected void handle(Update update) {
    editMessage(update, Words.CHANGE_REGION.lang(lang), InlineButton.regions(), Formatting.BOLD);
  }

  private District getDistrictFromData(String data) {
    return Arrays.stream(District.values())
        .filter(val -> val.getDistrict().equals(data))
        .findFirst()
        .orElse(null);
  }

  @Override
  public String getName() {
    return "change location";
  }
}
