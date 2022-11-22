package osonsot.mainbot.command.inline.location;

import static osonsot.config.BotConfig.DATA_SEPARATOR;
import static osonsot.mainbot.enums.localization.Words.CHANGE_DISTRICT;
import static osonsot.mainbot.enums.localization.Words.CHOOSE_DISTRICT;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.command.CallbackCommand;
import osonsot.button.InlineButton;
import osonsot.mainbot.enums.Formatting;
import osonsot.service.BotService;

@Component
public class Region extends CallbackCommand {

  @Override
  public String data() {
    return "reg";
  }

  public Region(BotService service) {
    super(service);
  }

  @Override
  protected void handle(Update update) {
    osonsot.mainbot.enums.Region region = getRegionFromData(update);
    editMessage(
        update,
        !service.existsByChatId(chatId) ? CHOOSE_DISTRICT.lang(lang) : CHANGE_DISTRICT.lang(lang),
        InlineButton.district(region),
        Formatting.BOLD);
  }

  private osonsot.mainbot.enums.Region getRegionFromData(Update update) {
    String data = update.getCallbackQuery().getData();
    String region = data.split(DATA_SEPARATOR)[1];
    Optional<osonsot.mainbot.enums.Region> first =
        Arrays.stream(osonsot.mainbot.enums.Region.values())
            .filter(val -> val.getRegion().equals(region))
            .findFirst();
    return first.orElse(null);
  }

  @Override
  public String getName() {
    return "region";
  }
}
