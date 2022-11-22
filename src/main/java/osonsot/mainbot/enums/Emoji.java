package osonsot.mainbot.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Emoji {
    FLAG_UZ("\uD83C\uDDFA\uD83C\uDDFF"),
    FLAG_RU("\uD83C\uDDF7\uD83C\uDDFA"),
    CONTACT("\uD83D\uDCDE"),
    PAPER("\uD83D\uDCC3"),
    ALERT("⚠️"),
    LOCK("\uD83D\uDD12"),
    SETTING("⚙️"),
    GLOBE("\uD83C\uDF10"),
    LOCATION("\uD83D\uDCCD"),
    PLUS("➕"),
    NOTIFICATION_ON("\uD83D\uDD09"),
    NOTIFICATION_OFF("\uD83D\uDD07"),
    PHONE("\uD83D\uDCF1"),
    BACK("◀️"),
    CORRECT("✅"),
    TENT("⛺️"),
    PAPERS("\uD83D\uDDDE"),
    BUTTON("\uD83D\uDD18"),
    MINUS("⛔️"),
    EDIT("✏️"),
    CANCEL("❌"),
    FLAG_EN("\uD83C\uDDEC\uD83C\uDDE7"),
    INFO("ℹ️"),
    SAVE("\uD83D\uDCBE");
    private final String emoji;

    public String get() {
        return emoji;
    }
}
