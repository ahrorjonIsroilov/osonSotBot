package osonsot.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {
    public static String BOT_USERNAME = "@osonsot_bot";
    public static String BOT_TOKEN = "5477032442:AAHF1sK9cMdugFNKv5JjtkbUE2lpJb4Kokc";
    public static Boolean USE_WEBHOOK = false;
    public static String COMMAND_INIT = "/";
}
