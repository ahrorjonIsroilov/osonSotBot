package osonsot.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {
  public static String BOT_USERNAME = "@osonsot_bot";
  public static String CHAT_BOT_USERNAME = "@osonsot_chatbot";
  public static String BOT_TOKEN = "5786411179:AAGZ9DKky0u0AdD55CAx9AZGGt-gzrvypJo";
  public static String CHAT_BOT_TOKEN = "5623877785:AAHiHS9beTTjOCFva-CacLMVmzQsMPNeAj0";
  public static Boolean USE_WEBHOOK = false;
  public static String COMMAND_INIT = "/";
  public static String DATA_SEPARATOR = "::";
}
