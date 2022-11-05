package osonsot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Regions {
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
