package osonsot.button;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import osonsot.entity.auth.AuthUser;
import osonsot.entity.auth.SessionUser;
import osonsot.entity.poster.Category;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Emoji;
import osonsot.mainbot.enums.Region;
import osonsot.mainbot.enums.localization.ButtonTitle;
import osonsot.mainbot.enums.localization.Language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static osonsot.config.BotConfig.DATA_SEPARATOR;

@Component
public class InlineButton {
    private static final InlineKeyboardMarkup board = new InlineKeyboardMarkup();

    public static InlineKeyboardMarkup chooseLang(SessionUser session) {
        Language langCode = Language.DEFAULT;
        if (session.getLangCode() != null) langCode = session.getLangCode();
        InlineKeyboardButton uz =
                new InlineKeyboardButton(
                        langCode.equals(Language.UZBEK)
                                ? Emoji.BUTTON.get() + Emoji.FLAG_UZ.get()
                                : Emoji.FLAG_UZ.get());
        uz.setCallbackData("lang" + DATA_SEPARATOR + "uz");
        InlineKeyboardButton ru =
                new InlineKeyboardButton(
                        langCode.equals(Language.RUSSIAN)
                                ? Emoji.BUTTON.get() + Emoji.FLAG_RU.get()
                                : Emoji.FLAG_RU.get());
        ru.setCallbackData("lang" + DATA_SEPARATOR + "ru");
        InlineKeyboardButton en =
                new InlineKeyboardButton(
                        langCode.equals(Language.ENGLISH)
                                ? Emoji.BUTTON.get() + Emoji.FLAG_EN.get()
                                : Emoji.FLAG_EN.get());
        en.setCallbackData("lang" + DATA_SEPARATOR + "en");
        board.setKeyboard(Collections.singletonList(getRow(uz, en, ru)));
        return board;
    }

    public static InlineKeyboardMarkup userSettings(AuthUser user) {
        InlineKeyboardButton language =
                new InlineKeyboardButton(ButtonTitle.LANG.get(user.getLanguage()));
        language.setCallbackData("chLang");
        InlineKeyboardButton location =
                new InlineKeyboardButton(ButtonTitle.LOCATION.get(user.getLanguage()));
        location.setCallbackData("chLocation");
        InlineKeyboardButton notifications =
                new InlineKeyboardButton(
                        !user.getNotification()
                                ? Emoji.NOTIFICATION_OFF.get() + ButtonTitle.NOTIFICATIONS.get(user.getLanguage())
                                : Emoji.NOTIFICATION_ON.get() + ButtonTitle.NOTIFICATIONS.get(user.getLanguage()));
        notifications.setCallbackData("notification");
        InlineKeyboardButton addEditExtraContact = new InlineKeyboardButton();
        if (user.getExtraContact() != null) {
            addEditExtraContact.setText(ButtonTitle.EDIT_CONTACT.get(user.getLanguage()));
            addEditExtraContact.setCallbackData("eContact::" + user.getExtraContact());
        } else {
            addEditExtraContact.setText(ButtonTitle.ADD_CONTACT.get(user.getLanguage()));
            addEditExtraContact.setCallbackData("aContact");
        }
        board.setKeyboard(
                Arrays.asList(
                        getRow(language, location),
                        getRow(addEditExtraContact),
                        getRow(notifications),
                        getRow(back(user.getLanguage()))));
        return board;
    }

    public static InlineKeyboardMarkup editContact(String contact, Language lang) {
        InlineKeyboardButton phone = new InlineKeyboardButton(contact);
        phone.setCallbackData("editContact");
        InlineKeyboardButton delete = new InlineKeyboardButton(ButtonTitle.DEL_CONTACT.get(lang));
        delete.setCallbackData("delContact");
        board.setKeyboard(Arrays.asList(getRow(phone), getRow(delete), getRow(back(lang))));
        return board;
    }

    private static InlineKeyboardButton back(Language language) {
        InlineKeyboardButton back = new InlineKeyboardButton(ButtonTitle.BACK.get(language));
        back.setCallbackData("back");
        return back;
    }

    private static InlineKeyboardButton home(Language language) {
        InlineKeyboardButton back = new InlineKeyboardButton(ButtonTitle.HOME.get(language));
        back.setCallbackData("home");
        return back;
    }

    public static InlineKeyboardMarkup userMainDashboard(Language lang) {
        InlineKeyboardButton postAd = new InlineKeyboardButton(ButtonTitle.POST_ANNOUNCEMENT.get(lang));
        postAd.setCallbackData("postAd");
        InlineKeyboardButton settings = new InlineKeyboardButton(ButtonTitle.SETTINGS.get(lang));
        settings.setCallbackData("settings");
        InlineKeyboardButton myAds = new InlineKeyboardButton(ButtonTitle.MY_ADS.get(lang));
        myAds.setCallbackData("my-ads::first::0");
        board.setKeyboard(Arrays.asList(getRow(postAd), getRow(myAds), getRow(settings)));
        return board;
    }

    public static InlineKeyboardMarkup myPosters(Poster poster, Language lang) {
        InlineKeyboardButton next = new InlineKeyboardButton("➡️");
        next.setCallbackData("my-ads::next::" + poster.getId());
        InlineKeyboardButton prev = new InlineKeyboardButton("⬅️");
        prev.setCallbackData("my-ads::prev::" + poster.getId());
        InlineKeyboardButton close = new InlineKeyboardButton(ButtonTitle.CLOSE.get(lang));
        close.setCallbackData("my-ads::close::" + poster.getId());
        InlineKeyboardButton edit = new InlineKeyboardButton(ButtonTitle.EDIT.get(lang));
        edit.setCallbackData("my-ads::edit::" + poster.getId());
        board.setKeyboard(List.of(getRow(prev, edit, next), getRow(close)));
        return board;
    }

    public static InlineKeyboardMarkup seePoster(Poster poster, Language language) {
        InlineKeyboardButton see = new InlineKeyboardButton(
                poster.getAccepted() ? ButtonTitle.SEE.get(language) : ButtonTitle.EDIT.get(language)
        );
        see.setCallbackData("see-poster::" + poster.getId());
        board.setKeyboard(List.of(getRow(see)));
        return board;
    }

    public static InlineKeyboardMarkup myCurrentPoster(
            Poster poster, Language lang, Boolean confirmSold, Boolean confirmDelete) {
        InlineKeyboardButton markAsSold =
                new InlineKeyboardButton(
                        poster.getSold()
                                ? ButtonTitle.POSTER_SOLD.get(lang)
                                : ButtonTitle.MARK_POSTER_AS_SOLD.get(lang));
        markAsSold.setCallbackData("my-ads::mark-as-sold::" + poster.getId());
        InlineKeyboardButton delete = new InlineKeyboardButton(ButtonTitle.DELETE.get(lang));
        delete.setCallbackData("my-ads::delete::" + poster.getId());
        InlineKeyboardButton backToList = new InlineKeyboardButton(ButtonTitle.BACK_TO_LIST.get(lang));
        backToList.setCallbackData("my-ads::back-to-list::" + poster.getId());
        InlineKeyboardButton confirmDeleteBtn = new InlineKeyboardButton(ButtonTitle.CONFIRM.get(lang));
        confirmDeleteBtn.setCallbackData("my-ads::confirm-delete::" + poster.getId());
        InlineKeyboardButton confirmMarkAsSold =
                new InlineKeyboardButton(ButtonTitle.CONFIRM.get(lang));
        confirmMarkAsSold.setCallbackData("my-ads::confirm-sold::" + poster.getId());
        InlineKeyboardButton cancel = new InlineKeyboardButton(ButtonTitle.CANCEL.get(lang));
        cancel.setCallbackData("my-ads::cancel::" + poster.getId());
        if (confirmDelete) {
            board.setKeyboard(List.of(getRow(confirmDeleteBtn, cancel)));
        } else if (confirmSold) {
            board.setKeyboard(List.of(getRow(confirmMarkAsSold, cancel)));
        } else board.setKeyboard(List.of(getRow(markAsSold), getRow(delete), getRow(backToList)));
        return board;
    }

    public static InlineKeyboardMarkup adminMainDashboard() {
        InlineKeyboardButton categories = new InlineKeyboardButton("Categories 🗂");
        categories.setCallbackData("categories");
        InlineKeyboardButton recentPosters = new InlineKeyboardButton("Recent posters 📦");
        recentPosters.setCallbackData("recents");
        InlineKeyboardButton sendAd = new InlineKeyboardButton("Send AD 💠");
        sendAd.setCallbackData("sendAd");
        InlineKeyboardButton stats = new InlineKeyboardButton("Stats 📊");
        stats.setCallbackData("stats");
        board.setKeyboard(
                Arrays.asList(getRow(categories), getRow(recentPosters), getRow(sendAd, stats)));
        return board;
    }

    public static InlineKeyboardMarkup placePoster(Language language) {
        InlineKeyboardButton confirm = new InlineKeyboardButton(ButtonTitle.READY_POSTER.get(language));
        confirm.setCallbackData("placePoster");
        InlineKeyboardButton delete = new InlineKeyboardButton(ButtonTitle.CANCEL_POSTER.get(language));
        delete.setCallbackData("deletePoster");
        board.setKeyboard(List.of(getRow(confirm, delete)));
        return board;
    }

    public static InlineKeyboardMarkup confirmPoster(Poster poster) {
        InlineKeyboardButton confirm = new InlineKeyboardButton("Confirm " + Emoji.CORRECT.get());
        confirm.setCallbackData("recent::confirm::" + poster.getId());
        InlineKeyboardButton delete = new InlineKeyboardButton("Reject " + Emoji.CANCEL.get());
        delete.setCallbackData("recent::reject::" + poster.getId());
        InlineKeyboardButton close = new InlineKeyboardButton("Close");
        close.setCallbackData("close");
        InlineKeyboardButton userInfo = new InlineKeyboardButton("Owner info 🧑🏻‍💼");
        userInfo.setCallbackData("user::" + poster.getOwner().getId());
        InlineKeyboardButton blockUser = new InlineKeyboardButton("Block owner 🚫");
        blockUser.setCallbackData("block-user::" + poster.getOwner().getId());
        board.setKeyboard(List.of(getRow(confirm, delete), getRow(userInfo, blockUser), getRow(close)));
        return board;
    }

    public static InlineKeyboardMarkup blockPeriod(Long userId) {
        InlineKeyboardButton oneWeek = new InlineKeyboardButton("block for 1 week");
        oneWeek.setCallbackData("block::1-week::" + userId);
        InlineKeyboardButton oneMonth = new InlineKeyboardButton("block for 1 month");
        oneMonth.setCallbackData("block::1-month::" + userId);
        InlineKeyboardButton threeMonth = new InlineKeyboardButton("block for 3 month");
        threeMonth.setCallbackData("block::3-month::" + userId);
        board.setKeyboard(List.of(
                getRow(oneWeek, oneMonth),
                getRow(threeMonth)
        ));
        return board;
    }

    public static InlineKeyboardMarkup categoryList(
            List<Category> categories, Integer parentId, Language lang, SessionUser session) {
        List<List<InlineKeyboardButton>> buttonList = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton addButton = new InlineKeyboardButton("Add ➕");
        addButton.setCallbackData(parentId != null ? "addCategory::" + parentId : "addCategory::null");
        InlineKeyboardButton editCategory = new InlineKeyboardButton("Edit ✏️");
        editCategory.setCallbackData("edit-cat::" + parentId);
        InlineKeyboardButton delCategory = new InlineKeyboardButton("Delete ❌");
        delCategory.setCallbackData("del-cat::" + parentId);
        if (!categories.isEmpty()) {
            buttons = prepareCategoryButtons(categories, lang);
        }
        for (int i = 0; i < buttons.size() - 1; i += 2) {
            buttonList.add(getRow(buttons.get(i), buttons.get(i + 1)));
        }
        if (buttons.size() % 2 != 0) buttonList.add(getRow(buttons.get(buttons.size() - 1)));
        switch (session.getRole()) {
            case ADMIN, OWNER -> {
                if (parentId == null) buttonList.add(getRow(addButton, home(lang)));
                else {
                    buttonList.add(getRow(addButton, editCategory, delCategory));
                    buttonList.add(getRow(back(lang), home(lang)));
                }
            }
            default -> {
                buttonList.add(getRow(home(lang)));
            }
        }

        board.setKeyboard(buttonList);
        return board;
    }

    private static List<InlineKeyboardButton> prepareCategoryButtons(
            List<Category> list, Language lang) {
        List<InlineKeyboardButton> categories = new ArrayList<>();
        for (Category category : list) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            switch (lang) {
                case RUSSIAN -> button.setText(category.getNameRu());
                case ENGLISH -> button.setText(category.getNameEn());
                case UZBEK -> button.setText(category.getNameUz());
            }
            button.setCallbackData("cat::" + category.getId());
            categories.add(button);
        }
        return categories;
    }

    public static InlineKeyboardMarkup district(Region region) {
        List<List<InlineKeyboardButton>> buttonList = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        List<District> districts =
                Arrays.stream(District.values()).filter(reg -> reg.getRegion().equals(region)).toList();
        for (District district : districts) {
            InlineKeyboardButton districtButton = new InlineKeyboardButton(district.getName());
            districtButton.setCallbackData("dis::" + district.getDistrict());
            buttons.add(districtButton);
        }
        for (int i = 0; i < buttons.size() - 1; i += 2) {
            buttonList.add(getRow(buttons.get(i), buttons.get(i + 1)));
        }
        if (buttons.size() % 2 != 0) buttonList.add(getRow(buttons.get(buttons.size() - 1)));
        board.setKeyboard(buttonList);
        return board;
    }

    public static InlineKeyboardMarkup regions() {
        InlineKeyboardButton tashkent = new InlineKeyboardButton(Region.TASHKENT.getRegion());
        tashkent.setCallbackData("reg::Toshkent");
        InlineKeyboardButton tashkentCity = new InlineKeyboardButton(District.TASHKENT_CITY.getName());
        tashkentCity.setCallbackData("dis::TashkentCity");
        InlineKeyboardButton bukhara = new InlineKeyboardButton(Region.BUKHARA.getRegion());
        bukhara.setCallbackData("reg::Buxoro");
        InlineKeyboardButton andijan = new InlineKeyboardButton(Region.ANDIJAN.getRegion());
        andijan.setCallbackData("reg::Andijon");
        InlineKeyboardButton khorezm = new InlineKeyboardButton(Region.KHOREZM.getRegion());
        khorezm.setCallbackData("reg::Xorazm");
        InlineKeyboardButton samarkand = new InlineKeyboardButton(Region.SAMARKAND.getRegion());
        samarkand.setCallbackData("reg::Samarqand");
        InlineKeyboardButton kashkadarya = new InlineKeyboardButton(Region.KASHKADARYA.getRegion());
        kashkadarya.setCallbackData("reg::Qashqadaryo");
        InlineKeyboardButton jizzakh = new InlineKeyboardButton(Region.JIZZAKH.getRegion());
        jizzakh.setCallbackData("reg::Jizzax");
        InlineKeyboardButton navoi = new InlineKeyboardButton(Region.NAVOI.getRegion());
        navoi.setCallbackData("reg::Navoi");
        InlineKeyboardButton surkhandarya = new InlineKeyboardButton(Region.SURKHANDARYA.getRegion());
        surkhandarya.setCallbackData("reg::Surxondaryo");
        InlineKeyboardButton namangan = new InlineKeyboardButton(Region.NAMANGAN.getRegion());
        namangan.setCallbackData("reg::Namangan");
        InlineKeyboardButton syrdarya = new InlineKeyboardButton(Region.SYRDARYA.getRegion());
        syrdarya.setCallbackData("reg::Sirdaryo");
        InlineKeyboardButton fergana = new InlineKeyboardButton(Region.FERGANA.getRegion());
        fergana.setCallbackData("reg::Farg'ona");
        InlineKeyboardButton karakalpakstan =
                new InlineKeyboardButton(Region.KARAKALPAKSTAN.getRegion());
        karakalpakstan.setCallbackData("reg::Qoraqalpoqiston");
        board.setKeyboard(
                Arrays.asList(
                        getRow(andijan, bukhara),
                        getRow(khorezm, samarkand),
                        getRow(kashkadarya, jizzakh),
                        getRow(namangan, surkhandarya),
                        getRow(syrdarya, fergana),
                        getRow(navoi, karakalpakstan),
                        getRow(tashkent, tashkentCity)));
        return board;
    }

    private static List<InlineKeyboardButton> getRow(InlineKeyboardButton... buttons) {
        return Arrays.stream(buttons).collect(Collectors.toList());
    }

    public static InlineKeyboardMarkup close(Language lang) {
        InlineKeyboardButton close = new InlineKeyboardButton(ButtonTitle.CLOSE.get(lang));
        close.setCallbackData("close");
        board.setKeyboard(List.of(getRow(close)));
        return board;
    }

    public static InlineKeyboardMarkup edit(Poster poster, Language lang) {
        InlineKeyboardButton editPic = new InlineKeyboardButton(ButtonTitle.EDIT_POSTER_PIC.get(lang));
        editPic.setCallbackData("edit-poster::edit-pic::" + poster.getId());
        InlineKeyboardButton editTitle = new InlineKeyboardButton(ButtonTitle.EDIT_POSTER_TITLE.get(lang));
        editTitle.setCallbackData("edit-poster::edit-title::" + poster.getId());
        InlineKeyboardButton editDescription = new InlineKeyboardButton(ButtonTitle.EDIT_POSTER_DESCRIPTION.get(lang));
        editDescription.setCallbackData("edit-poster::edit-description::" + poster.getId());
        InlineKeyboardButton editPrice = new InlineKeyboardButton(ButtonTitle.EDIT_POSTER_PRICE.get(lang));
        editPrice.setCallbackData("edit-poster::edit-price::" + poster.getId());
        InlineKeyboardButton editCategory = new InlineKeyboardButton(ButtonTitle.EDIT_POSTER_CATEGORY.get(lang));
        editCategory.setCallbackData("edit-poster::edit-category::" + poster.getId());
        InlineKeyboardButton saveAll = new InlineKeyboardButton(ButtonTitle.SAVE.get(lang));
        saveAll.setCallbackData("edit-poster::save-all::" + poster.getId());
        board.setKeyboard(List.of(
                getRow(editPic),
                getRow(editTitle, editDescription),
                getRow(editPrice, editCategory),
                getRow(saveAll)));
        return board;
    }

    public static InlineKeyboardMarkup rejectionCause(Poster poster, Language lang) {
        InlineKeyboardButton cause1 = new InlineKeyboardButton("Picture problem 🖼");
        cause1.setCallbackData("reject::1::" + lang.getLangCode() + "::" + poster.getId());
        InlineKeyboardButton cause2 = new InlineKeyboardButton("Title problem 📎");
        cause2.setCallbackData("reject::2::" + lang.getLangCode() + "::" + poster.getId());
        InlineKeyboardButton cause3 = new InlineKeyboardButton("Description problem 📃");
        cause3.setCallbackData("reject::3::" + lang.getLangCode() + "::" + poster.getId());
        InlineKeyboardButton cause4 = new InlineKeyboardButton("Category problem 📁");
        cause4.setCallbackData("reject::4::" + lang.getLangCode() + "::" + poster.getId());
        InlineKeyboardButton cause5 = new InlineKeyboardButton("Sensual content 🔞");
        cause5.setCallbackData("reject::5::" + lang.getLangCode() + "::" + poster.getId());
        InlineKeyboardButton cause6 = new InlineKeyboardButton("Gambling related 🎲");
        cause6.setCallbackData("reject::6::" + lang.getLangCode() + "::" + poster.getId());
        InlineKeyboardButton cause7 = new InlineKeyboardButton("Violence 🔫");
        cause7.setCallbackData("reject::7::" + lang.getLangCode() + "::" + poster.getId());
        InlineKeyboardButton cause8 = new InlineKeyboardButton("Other reasons ⁉️");
        cause8.setCallbackData("reject::8::" + lang.getLangCode() + "::" + poster.getId());
        board.setKeyboard(
                List.of(
                        getRow(cause1, cause2),
                        getRow(cause3, cause4),
                        getRow(cause5, cause6),
                        getRow(cause7, cause8)));
        return board;
    }
}
