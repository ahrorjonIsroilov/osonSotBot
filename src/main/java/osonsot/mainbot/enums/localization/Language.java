package osonsot.mainbot.enums.localization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
  UZBEK("uz"),
  RUSSIAN("ru"),
  ENGLISH("en"),
  DEFAULT("default");
  private final String langCode;

  public Language getByLangCode(String langCode) {
    switch (langCode) {
      case "uz" -> {
        return UZBEK;
      }
      case "ru" -> {
        return RUSSIAN;
      }
      case "en" -> {
        return ENGLISH;
      }
      default -> {
        return DEFAULT;
      }
    }
  }
}
