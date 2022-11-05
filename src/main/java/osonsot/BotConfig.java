package osonsot;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {
    @Value("${telegram.bot-name}")
    public static String BOT_USERNAME = "@osonsot_bot";
    @Value("${telegram.bot-token}")
    public static String BOT_TOKEN = "5477032442:AAECQriDE71D-kRzgFAaqrae1GMeyP7mEMU";
    public static String WEBHOOK_PATH = "https://057e-89-146-124-119.eu.ngrok.io";
    public static Boolean USE_WEBHOOK = true;
}
