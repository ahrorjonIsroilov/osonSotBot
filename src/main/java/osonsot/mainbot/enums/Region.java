package osonsot.mainbot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Region {
  TASHKENT("Toshkent"),
  KARAKALPAKSTAN("Qoraqalpoqiston"),
  BUKHARA("Buxoro"),
  ANDIJAN("Andijon"),
  KHOREZM("Xorazm"),
  SAMARKAND("Samarqand"),
  KASHKADARYA("Qashqadaryo"),
  JIZZAKH("Jizzax"),
  NAVOI("Navoi"),
  SURKHANDARYA("Surxondaryo"),
  NAMANGAN("Namangan"),
  SYRDARYA("Sirdaryo"),
  FERGANA("Farg'ona");
  private final String region;
}
