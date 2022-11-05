package osonsot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Districts {
    AMUDARYO(Regions.KARAKALPAKSTAN, "Amudaryo"),
    BERUNIY(Regions.KARAKALPAKSTAN, "Beruniy"),
    CHIMBOY(Regions.KARAKALPAKSTAN, "Chimboy"),
    ELLIKQALA(Regions.KARAKALPAKSTAN, "Ellikqal'a"),
    KEGEYLI(Regions.KARAKALPAKSTAN, "Kegeyli"),
    MOYNOQ(Regions.KARAKALPAKSTAN, "Mo'ynoq"),
    NUKUS(Regions.KARAKALPAKSTAN, "Nukus"),
    QANLIKOL(Regions.KARAKALPAKSTAN, "Qaniko'l"),
    QONGIROT(Regions.KARAKALPAKSTAN, "Qo'ng'irot"),
    QORAOZAK(Regions.KARAKALPAKSTAN, "Qorao'zak"),
    SHUMANAY(Regions.KARAKALPAKSTAN, "Shumanay"),
    TAXTAKOPIR(Regions.KARAKALPAKSTAN, "Taxtako'pir"),
    TORTKOL(Regions.KARAKALPAKSTAN, "To'rtko'l"),
    XOJAYLI(Regions.KARAKALPAKSTAN, "Xo'jayli"),
    BOGOT(Regions.KHOREZM, "Bog'ot"),
    GURLEN(Regions.KHOREZM, "Gurlen"),
    XONQA(Regions.KHOREZM, "Xonqa"),
    HAZORASP(Regions.KHOREZM, "Hazorasp"),
    XIVA(Regions.KHOREZM, "Xiva"),
    QOSHKOPIR(Regions.KHOREZM, "Qo'shko'pir"),
    SHOVUT(Regions.KHOREZM, "Shovut"),
    URGANCH(Regions.KHOREZM, "Urganch"),
    YANGIARIQ(Regions.KHOREZM, "Yangiariq"),
    KANIMEH(Regions.KHOREZM, "Kanimeh"),
    NAVOIY(Regions.NAVOI, "Navoiy"),
    QIZILTEPA(Regions.NAVOI, "Qiziltepa"),
    XATIRCHI(Regions.NAVOI, "Xatirchi"),
    NAVBAHOR(Regions.NAVOI, "Navbahor"),
    NUROTA(Regions.NAVOI, "Nurota"),
    TAMDI(Regions.NAVOI, "Tamdi"),
    UCHQUDUQ(Regions.NAVOI, "Uchquduq");
    private final Regions region;
    private final String district;
}
