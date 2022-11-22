package osonsot.mainbot.enums.localization;

import lombok.AllArgsConstructor;
import osonsot.mainbot.enums.Emoji;

@AllArgsConstructor
public enum ButtonTitle {
    POST_ANNOUNCEMENT(
            Emoji.PAPER.get() + " Эълон жойлаш",
            Emoji.PAPER.get() + " Разместить объявление",
            Emoji.PAPER.get() + " Post an announcement"),
    READY_POSTER(
            "Тайёр " + Emoji.CORRECT.get(),
            "Готовый " + Emoji.CORRECT.get(),
            "Ready " + Emoji.CORRECT.get()),
    CANCEL_POSTER(
            "Ўчириш " + Emoji.CANCEL.get(),
            "Удалить " + Emoji.CANCEL.get(),
            "Remove " + Emoji.CANCEL.get()),
    MARK_POSTER_AS_SOLD(
            "Сотилган деб белгилаш " + Emoji.ALERT.get(),
            "Отметить как проданное " + Emoji.ALERT.get(),
            "Mark as sold " + Emoji.ALERT.get()),
    POSTER_SOLD(
            "Сотилган " + Emoji.LOCK.get(),
            "Распроданный " + Emoji.LOCK.get(),
            "Sold " + Emoji.LOCK.get()),
    SETTINGS(
            Emoji.SETTING.get() + " Созламалар",
            Emoji.SETTING.get() + " Настройки",
            Emoji.SETTING.get() + " Settings"),
    LANG(Emoji.GLOBE.get() + " Тил", Emoji.GLOBE.get() + " Язык", Emoji.GLOBE.get() + " Language"),
    LOCATION(
            Emoji.LOCATION.get() + " Жойлашув",
            Emoji.LOCATION.get() + " Расположение",
            Emoji.LOCATION.get() + " Location"),
    NOTIFICATIONS("Билдиришномалар", "Уведомления", "Notifications"),
    ADD_CONTACT(
            Emoji.PLUS.get() + " Контакт қўшиш",
            Emoji.PLUS.get() + " Добавить контакт",
            Emoji.PLUS.get() + " Add contact"),
    EDIT_CONTACT(
            Emoji.PHONE.get() + " Контактни таҳрирлаш",
            Emoji.PHONE.get() + " Изменить контакт",
            Emoji.PHONE.get() + " Edit contact"),
    EDIT(Emoji.EDIT.get() + " Таҳрирлаш", Emoji.EDIT.get() + " Изменить", Emoji.EDIT.get() + " Edit"),
    BACK(Emoji.BACK.get() + " Орқага", Emoji.BACK.get() + " Назад", Emoji.BACK.get() + " Back"),
    HOME(
            Emoji.TENT.get() + " Бош меню",
            Emoji.TENT.get() + " Главное меню",
            Emoji.TENT.get() + " Main menu"),
    MY_ADS(
            Emoji.PAPERS.get() + " Эълонларим",
            Emoji.PAPERS.get() + " Мои объявления",
            Emoji.PAPERS.get() + " My announcements"),
    SHARE_CONTACT(
            "Контактни улашиш " + Emoji.CONTACT.get(),
            "Поделиться контактом " + Emoji.CONTACT.get(),
            "Share contact " + Emoji.CONTACT.get()),
    DEL_CONTACT(
            "Контактни ўчириш" + Emoji.MINUS.get(),
            "Удалить контакт" + Emoji.MINUS.get(),
            "Delete contact" + Emoji.MINUS.get()),
    DELETE(
            "Ўчириш " + Emoji.MINUS.get(), "Удалить " + Emoji.MINUS.get(), "Delete " + Emoji.MINUS.get()),
    CANCEL(
            "Бекор қилиш " + Emoji.CANCEL.get(),
            "Отмена " + Emoji.CANCEL.get(),
            "Cancel " + Emoji.CANCEL.get()),
    CONFIRM(
            "Тасдиқлаш " + Emoji.CORRECT.get(),
            "Подтверждение " + Emoji.CORRECT.get(),
            "Confirm " + Emoji.CORRECT.get()),
    BACK_TO_LIST(
            "Листга қайтиш " + Emoji.BACK.get(),
            "Вернуться к списку " + Emoji.BACK.get(),
            "Return to list " + Emoji.BACK.get()),
    CLOSE("Ёпиш", "Закрывать", "Close"),
    SEE("Кўриш", "Видеть", "View"),
    EDIT_POSTER_TITLE("Сарлавҳа", "Название", "Title"),
    EDIT_POSTER_PIC("Расм", "Фото", "Photo"),
    EDIT_POSTER_DESCRIPTION("Тавсиф", "Описание", "Description"),
    EDIT_POSTER_PRICE("Нарх", "Цену", "Price"),
    EDIT_POSTER_CATEGORY("Категория", "Категория", "Category"),
    SAVE(
            "Сақлаш " + Emoji.SAVE.get(),
            "Сохранять " + Emoji.SAVE.get(),
            "Save " + Emoji.SAVE.get());
    private final String uz;
    private final String ru;
    private final String en;

    public String get(Language language) {
        switch (language) {
            case ENGLISH -> {
                return this.en;
            }
            case RUSSIAN -> {
                return this.ru;
            }
            default -> {
                return this.uz;
            }
        }
    }

    public String getDefault(String title) {
        if (title.equals(this.uz)) return this.uz;
        else if (title.equals(this.en)) return this.en;
        else return this.ru;
    }
}
