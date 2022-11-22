package osonsot.mainbot.enums.localization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import osonsot.mainbot.enums.Emoji;

@Getter
@AllArgsConstructor
public enum Words {
    POSTER_PLACED(
            "✅ Эълон жойлаштирилди. Админларимиз тасдиғидан ўтгач эълонингиз қидирувда байдо бўлади.",
            "✅ Объявление размещено. Ваше объявление появится в поиске после подтверждения нашими администраторами.",
            "✅ The announcement has been posted. Your announcement will appear in the search after confirmation by our admins."),
    POSTER_REMOVED(
            "Жорий эълон ўчириб ташланди ✅",
            "Текущее объявление удалено ✅",
            "The current announcement has been deleted ✅"),
    POSTERS_NOT_AVAILABLE(
            "Сизда ҳали эълонлар мавжуд эмас!",
            "У вас еще нет объявления!",
            "You have no announcements yet!"),
    ONLY_UZB_PHONE(
            "Кечирасиз, чет эл телефон рақамлари қабул қилинмайди",
            "Извините, иностранные номера телефонов не принимаются",
            "Sorry, foreign phone numbers are not accepted"),
    INVALID_PHONE(
            "Нотўғри телефон рақами киритилди",
            "Введен неверный номер телефона",
            "Invalid phone number entered"),
    MAX_LENGTH_REACHED(
            Emoji.ALERT.get() + " Белгилар сони максимал миқдордан ошиб кетди",
            Emoji.ALERT.get() + " Превышено максимальное количество символов",
            Emoji.ALERT.get() + " The maximum number of characters has been exceeded"),
    CHOOSE_REGION("Қайси вилоятдансиз?", "Вы из какого региона?", "What region are you from?"),
    CHOOSE_REGION_1(
            "Жуда соз. Энди вилоятингизни танланг",
            "Очень хорошо. Теперь выберите свой регион",
            "Very nice. Now select your region"),
    CHANGE_REGION("Вилоятни танланг", "Выберите регион", "Select a region"),
    CHOOSE_DISTRICT(
            "Туманингизни танланг. Бу сизга энг яқиндаги эълонларни тавсия қилишимиз учун керак",
            "Выберите свой район. Это делается для того, чтобы мы могли рекомендовать наиболее близкие вам объявления.",
            "Select your district. This is so that we can recommend the ads that are closest to you"),
    CHANGE_DISTRICT("Туманингизни танланг", "Выберите свой район", "Select your district"),
    REGISTERED(
            "Рўйхатдан муваффақиятли ўтдингиз!",
            "Вы успешно зарегистрировались!",
            "You have successfully registered!"),
    REGION("Вилоят", "Провинция", "Region"),
    LANG_CHANGED(
            "Тил ўзгартирилди " + Emoji.CORRECT.get(),
            "Язык был изменен " + Emoji.CORRECT.get(),
            "The language has been changed " + Emoji.CORRECT.get()),
    LOCATION_CHANGED(
            "Жойлашув ўзгартирилди " + Emoji.CORRECT.get(),
            "Место было изменено " + Emoji.CORRECT.get(),
            "The location has been changed " + Emoji.CORRECT.get()),
    ALREADY_SOLD_POSTER(
            "Ушбу эълон аллақачон сотилган " + Emoji.ALERT.get(),
            "Это объявление уже продано " + Emoji.ALERT.get(),
            "This announcement has already been sold " + Emoji.ALERT.get()),
    CONTACT_DELETED(
            "Контакт ўчирилди " + Emoji.CORRECT.get(),
            "Контакт был удален " + Emoji.CORRECT.get(),
            "The contact has been deleted " + Emoji.CORRECT.get()),
    POSTER_DELETED(
            "Эълон ўчирилди " + Emoji.CORRECT.get(),
            "Объявление был удален " + Emoji.CORRECT.get(),
            "The announcement has been deleted " + Emoji.CORRECT.get()),
    DISTRICT("Туман", "Туман", "District"),
    CITY("Шаҳар", "Город", "City"),
    UNAVAILABLE(" Мавжуд эмас", " Нет в наличии", " Not available"),
    MAIN_MENU(
            "Асосий меню " + Emoji.TENT.get(),
            "Главное меню " + Emoji.TENT.get(),
            "Main menu " + Emoji.TENT.get()),
    ADD_CONTACT(
            "Қўшимча телефон рақамингизни киритинг" + Emoji.PHONE.get(),
            "Введите дополнительный номер телефона" + Emoji.PHONE.get(),
            "Enter your additional phone number" + Emoji.PHONE.get()),
    EDIT_CONTACT(
            "Телефон рақамни таҳрирлаш" + Emoji.PHONE.get(),
            "Изменить номер телефона" + Emoji.PHONE.get(),
            "Edit phone number" + Emoji.PHONE.get()),
    CATEGORY_INFO(
            """
                    Номи: <code>%s</code>
                    Субкатегориялар: <code>%s ta</code>
                    """,
            """
                    Имя: <code>%s</code>
                    Подкатегории: <code>%s</code>
                    """,
            """
                    Name: <code>%s</code>
                    Subcategories: <code>%s</code>
                    """),
    USER_INFO(
            """
                    📞 Телефон: <code>%s</code>
                    📱 Қўшимча рақам: <code>%s</code>
                    📍 Жойлашув: <code>%s</code>""",
            """
                    📞 Телефон: <code>%s</code>
                    📱 Дополнительный номер: <code>%s</code>
                    📍 Расположение: <code>%s</code>""",
            """
                    📞 Phone: <code>%s</code>
                    📱 Additional contact: <code>%s</code>
                    📍 Location: <code>%s</code>"""),
    SHARE_CONTACT(
            "Контактни улашиш тугмасини босинг",
            "Нажмите кнопку «Поделиться контактом»",
            "Click the share contact button"),
    CANCELLATION(
            "Жорий ҳаракат бекор қилинди",
            "Текущее действие было отменено",
            "The current action has been cancelled"),
    MAXIMUM_PHOTO_LIMIT(
            Emoji.ALERT.get()
                    + " Расмларингиз сони максимал миқдорга етди. \nКейинги қадамга ўтиш учун /done буйруғини юборинг.",
            Emoji.ALERT.get()
                    + " Вы достигли максимального количества фотографий.\nОтправьте команду /done, чтобы перейти к следующему шагу.",
            Emoji.ALERT.get()
                    + " You have reached the maximum number of photos.\nSend the /done command to go to the next step"),
    MAXIMUM_PHOTO_SIZE(
            Emoji.ALERT.get() + " Расм ҳажми 3Mb дан кичик бўлиши керак.",
            Emoji.ALERT.get() + " Размер изображения должен быть меньше 3 Mb.",
            Emoji.ALERT.get() + " Image size must be less than 3Mb."),
    SEND_PHOTOS_INDIVIDUALLY(
            Emoji.ALERT.get() + " Расмларни якка ҳолатда юборинг!",
            Emoji.ALERT.get() + " Отправляйте фотографии по отдельности!",
            Emoji.ALERT.get() + " Please send pictures individually!"),
    SUBMIT_OR_DONE(
            "Яна расм юборишингиз мумкин.\nКейинги қадамга ўтиш учун /done буйруғини юборинг",
            "Вы можете отправить другое изображение.\nОтправьте команду /done, чтобы перейти к следующему шагу",
            "You can send another image.\nSend the /done command to go to the next step"),
    NOTIFICATION_ON(
            "Билдиришномалар ёқилди " + Emoji.NOTIFICATION_ON.get(),
            "Уведомления включены " + Emoji.NOTIFICATION_ON.get(),
            "Notifications on " + Emoji.NOTIFICATION_ON.get()),
    NOTIFICATION_OFF(
            "Билдиришномалар ўчирилди" + Emoji.NOTIFICATION_OFF.get(),
            "Уведомления отключены" + Emoji.NOTIFICATION_OFF.get(),
            "Notification off " + Emoji.NOTIFICATION_OFF.get()),
    CHANGES_SAVED(
            "Ўзгаришлар сақланди" + Emoji.CORRECT.get(),
            "Изменения сохранены" + Emoji.CORRECT.get(),
            "Changes saved " + Emoji.CORRECT.get()),
    POST_UPDATED(
            "Ўзгаришлар сақланди. Яқин орада админларимиз постингизни қайта кўриб чиқишади. " + Emoji.CORRECT.get(),
            "Изменения сохранены. Наши администраторы рассмотрят ваш пост в ближайшее время. " + Emoji.CORRECT.get(),
            "Changes saved. Our admins will review your post soon. " + Emoji.CORRECT.get()),
    CHOOSE_CATEGORY(
            "Қайси категорияга тегишли эълон жойламоқчисиз?",
            "В какой категории вы хотите разместить объявление?",
            "Which category do you want to post an ad in?"),
    EDIT_CATEGORY(
            "Янги категорияни танланг.",
            "Выберите новую категорию.",
            "Select a new category."),
    SEND_POSTER_PHOTO(
            "Жуда соз! Энди эълонингиз учун расм юборинг.\n<i>(Эслатма: Расмларни якка ҳолатда юборинг)</i>",
            "Отправьте фото для вашего объявления.\n<i>(Примечание: Отправляйте фотографии по отдельности)</i>",
            "Very good! Now submit a photo for your announcement.\n<i>(Note: Send photos individually)</i>"),
    EDIT_POSTER_PHOTO(
            "Янги расм юборинг.\n<i>(Эслатма: Расмларни якка ҳолатда юборинг)</i>",
            "Отправить новую фото.\n<i>(Примечание: Отправляйте фотографии по отдельности)</i>",
            "Submit a new photo for your announcement\n<i>(Note: Send photos individually)</i>"),
    SEND_POSTER_NAME(
            "Эълонингиз учун сарлавҳа киритинг.\n<i>Эслатма: максимал 80 белги</i>",
            "Введите заголовок для вашего объявления.\n<i>Примечание: Максимум 80 символов</i>",
            "Enter a title for your announcement.\n<i>Note: Maximum 80 characters</i>"),
    EDIT_POSTER_NAME(
            "Янги сарлавҳани киритинг.\n<i>Эслатма: максимал 80 белги</i>",
            "Введите новый заголовок.\n<i>Примечание: Максимум 80 символов</i>",
            "Enter a new title for your announcement.\n<i>Note: Maximum 80 characters</i>"),
    ADD_CONTACT_ALERT(
            "Айни вақтда қўшимча телефон рақам қўшишнинг имкони йўқ.",
            "В настоящее время невозможно добавить дополнительный номер телефона.",
            "It is not possible to add an additional phone number at this time."),
    WRITE_DESCRIPTION(
            "Энди эълонингиз учун 3-4 қатор тавсиф ёзинг.\n<i>Эслатма: максимал 500 белги</i>",
            "Теперь напишите 3-4-строчное описание для вашего объявления.\n<i>Примечание: Максимум 500 символов</i>",
            "Now write a 3-4 line description for your announcement.\n<i>Note: Maximum 500 characters</i>"),
    EDIT_POSTER_DESCRIPTION(
            "Янги тавсифни киритинг.\n<i>Эслатма: максимал 500 белги</i>",
            "Введите новое описание.\n<i>Примечание: Максимум 500 символов</i>",
            "Enter a new description for your announcement.\n<i>Note: Maximum 500 characters</i>"),
    SEND_PRICE(
            "Энди ушбу эълонингиз учун нарх қўйинг.",
            "Теперь установить цену для этого объявления.",
            "Now set a price for this announcement."),
    EDIT_PRICE(
            "Янги нарх киритинг.",
            "Введите новую цену.",
            "Enter a new price for this announcement."),
    SOLD("Сотилди", "Распроданный", "Sold out"),
    ACCEPTED("Тасдиқланди ✓", "Подтвержденный ✓", "Confirmed ✓"),
    WAITING("Кўриб чиқилмоқда...", "На рассмотрении...", "Under review..."),
    ACTIVE("Актив", "Активный", "Active"),
    POSTER_INFO(
            """
                    <u>%s</u>

                    💰 Нарх: <code>%s</code>
                    📝 Тавсиф: <i>%s</i>
                    📍 Жойлашув: <i>%s</i>
                    👁‍🗨 Ҳолати: <i>%s</i>
                    ℹ️ Батафсил: <a href="%s">бу ерга босинг</a>
                    ====================================
                    ⏰ Юкланган сана: <i>%s</i>
                    📞 Алоқа учун: %s""",
            """
                    <u>%s</u>

                    💰 Цена: <code>%s</code>
                    📝 Описание: <i>%s</i>
                    📍 Расположение: <i>%s</i>
                    👁‍🗨 Статус: <i>%s</i>
                    ℹ️ Подробнее: <a href="%s">нажмите здесь</a>
                    ====================================
                    ⏰ Дата публикации: <i>%s</i>
                    📞 Для связаться: %s""",
            """
                    <u>%s</u>

                    💰 Price: <code>%s</code>
                    📝 Description: <i>%s</i>
                    📍 Location: <i>%s</i>
                    👁‍🗨 Status: <i>%s</i>
                    ℹ️ More: <a href="%s">click here</a>
                    ====================================
                    ⏰ Date of publication: <i>%s</i>
                    📞 To contact: %s"""),
    POSTER_INFO_FOR_OWNER(
            """
                    <u>%s</u>

                    💰 Нарх: <code>%s</code>
                    📝 Тавсиф: <i>%s</i>
                    📞 Алоқа учун: %s
                    ℹ️ Батафсил: <a href="%s">бу ерга босинг</a>
                    ====================================
                    ♾ Ҳолати: <i>%s</i>
                    📂 Категория: <i>%s</i>
                    ⏰ Юкланган сана: <i>%s</i>
                    """,
            """
                    <u>%s</u>

                    💰 Цена: <code>%s</code>
                    📝 Описание: <i>%s</i>
                    📞 Для связаться: %s
                    ℹ️ Подробнее: <a href="%s">нажмите здесь</a>
                    ====================================
                    ♾ Статус: <i>%s</i>
                    📂 Категория: <i>%s</i>
                    ⏰ Дата публикации: <i>%s</i>
                    """,
            """
                    <u>%s</u>

                    💰 Price: <code>%s</code>
                    📝 Description: <i>%s</i>
                    📞 To contact: %s
                    ℹ️ More: <a href="%s">click here</a>
                    ====================================
                    ♾ Status: <i>%s</i>
                    📂 Category: <i>%s</i>
                    ⏰ Date of publication: <i>%s</i>
                    """),
    POSTER_INFO_WITHOUT_URL(
            """
                    %s

                    💰 Нарх: %s
                    📝 Тавсиф: %s
                    📞 Алоқа учун: %s
                    ♾ Ҳолати: %s
                    📂 Категория: %s
                    ⏰ Юкланган сана: %s
                    """,
            """
                    %s

                    💰 Цена: %s
                    📝 Описание: %s
                    📞 Для связаться: %s
                    ♾ Статус: %s
                    📂 Категория: %s
                    ⏰ Дата публикации: %s
                    """,
            """
                    %s

                    💰 Price: %s
                    📝 Description: %s
                    📞 To contact: %s
                    ♾ Status: %s
                    📂 Category: %s
                    ⏰ Date of publication: %s
                    """),
    INVALID_PRICE(
            Emoji.ALERT.get() + " Нархни тўғри форматда киритинг.",
            Emoji.ALERT.get() + " Введите цену в правильном формате.",
            Emoji.ALERT.get() + " Enter the price in the correct format."),
    MARK_AS_SOLD(
            """
                    Ростдан ҳам ушбу эълонни сотилди деб белгиламоқчимсиз?

                    <i>⚠️ Диққат: ушбу буйруқни ортга қайтариб бўлмайди!</i>""",
            """
                    Вы уверены, что хотите пометить это объявление как проданное?

                    <i>⚠️ Внимание: эту команду нельзя отменить!</i>""",
            """
                    Are you sure you want to mark this listing as sold?

                    <i>⚠️ Attention: this command cannot be undone!</i>"""),
    CONFIRM_DELETE_POSTER(
            """
                    Ростдан ҳам ушбу эълонни ўчириб ташламоқчимисиз?

                    <i>⚠️ Диққат: ушбу буйруқни ортга қайтариб бўлмайди!</i>""",
            """
                    Вы уверены, что хотите удалить эту объявления?

                    <i>⚠️ Внимание: эту команду нельзя отменить!</i>""",
            """
                    Are you sure you want to delete this announcement?

                    <i>⚠️ Attention: this command cannot be undone!</i>"""),
    LAST_PAGE(
            "Охирги бўлимдасиз", "Вы находитесь в последнем разделе", "You are in the last section"),
    FIRST_PAGE(
            "Биринчи бўлимдасиз", "Вы находитесь в первом разделе", "You are in the first section"),
    NO_RECENTS(
            Emoji.INFO.get() + " Тасдиқланмаган эълонлар мавжуд эмас.",
            Emoji.INFO.get() + " Нет неподтвержденных объявлений.",
            Emoji.INFO.get() + " There are no unconfirmed announcements."),
    POSTER_CONFIRMED_NOTIFICATION(
            "✅ Ушбу эълонингиз админларимиз томонидан тасдиқланди. Эълонни кўриш учун пастдаги тугмани босинг.",
            "✅ Это объявление было одобрено нашими администраторами. Нажмите на кнопку ниже, чтобы просмотреть объявление.",
            "✅ This announcement has been confirmed by our admins. Click the button below to view the announcement."),
    REJECTION_1(
            "❌ Ушбу эълонингиз админлар томонидан тасдиқланмади. Расм бириктираётганда эълонингизни тасвирлаб бериш учун мақбул расмлардан фойдаланинг.",
            "❌ Ваше объявление не было одобрено администраторами. Прикрепляя изображение, используйте допустимые изображения для описания вашего объявления.",
            "❌ Your announcement has not been confirmed by admins. When attaching an image, use acceptable images to describe your announcement."),
    REJECTION_2(
            "❌ Ушбу эълонингиз админлар томонидан тасдиқланмади. Эълон учун сарлавҳа ёзаётганда унинг мавзуга доир эканлигига аҳамият беринг.",
            "❌ Ваше объявление не было одобрено администраторами. При написании заголовка для объявления убедитесь, что он соответствует теме.",
            "❌ Your announcement has not been approved by admins. When writing a headline for your announcement, make sure it's on-topic."
    ),
    REJECTION_3(
            "❌ Ушбу эълонингиз админлар томонидан тасдиқланмади. Эълон учун тавсиф ёзаётганда унинг мавзуга доир эканлигига аҳамият беринг.",
            "❌ Ваше объявление не было одобрено администраторами. При написании описания для вашего объявления убедитесь, что оно соответствует теме.",
            "❌ Your announcement has not been approved by admins. When writing a description for your announcement, make sure it's on-topic."
    ),
    REJECTION_4(
            "❌ Эълонингиз админлар томонидан тасдиқланмади. Эълонингиз категорияси унинг мавзусига алоқадор эмас. Илтимос шу нарсага аҳамият беринг.",
            "❌ Ваше объявление не было одобрено администраторами. Категория вашего объявления не связана с его темой. Пожалуйста, обратите на это внимание.",
            "❌ Your announcement has not been approved by admins. The category of your announcement is not related to its topic. Please pay attention to this."
    ),
    REJECTION_5(
            "❌ Эълонингиз админлар томонидан тасдиқланмади. Эълонингиз шаҳвоний контентни ўз ичига олади. Бундай ҳолатлар такрорланиши натижасида блокланишингиз мумкин.",
            "❌ Ваше объявление не было одобрено администраторами. Ваше объявление содержит материалы сексуального характера. Вы можете быть заблокированы в результате повторения таких ситуаций.",
            "❌ Your announcement has not been approved by admins. Your announcement contains sensual content. You may be blocked as a result of repeating such situations."
    ),
    REJECTION_6(
            "❌ Эълонингиз админлар томонидан тасдиқланмади. Эълонингиз қуморга асосланган контентни ўз ичига олган. Ушбу ҳолат бизнинг ички тартибларимизга зид.",
            "❌ Ваше объявление не было одобрено администраторами. Ваше объявление содержит азартные игры. Это противоречит нашим внутренним процедурам.",
            "❌ Your announcement has not been approved by admins. Your announcement contains gambling content. This is against our internal procedures."
    ),
    REJECTION_7(
            "❌ Эълонингиз админлар томонидан тасдиқланмади. Эълонингиз зўравонликга тарғиб қилувчи маҳсулотни кўрсатмоқда. Бу бизнинг ички тартибларимизга зид.",
            "❌ Ваше объявление не было одобрено администраторами. В вашем объявлении показан продукт, пропагандирующий насилие. Это противоречит нашим внутренним процедурам.",
            "❌ Your announcement has not been approved by admins. Your announcement displays a product that promotes violence. This is against our internal procedures."
    ),
    REJECTION_8(
            "❌ Эълонингиз админлар томонидан тасдиқланмади. Илтимос камчиликлар йўқлигига ишонч ҳосил қилиш учун эълонингизни яна бир бор текширинг.",
            "❌ Ваше объявление не было одобрено администраторами. Дважды проверьте свое объявление, чтобы убедиться в отсутствии ошибок.",
            "❌ Your announcement has not been approved by admins. Please double-check your announcement to make sure there are no lacks."
    ),
    POSTER_CREATED(
            "Эълон муваффақиятли яратилди " + Emoji.CORRECT.get(),
            "Объявление успешно создано " + Emoji.CORRECT.get(),
            "The announcement was created successfully " + Emoji.CORRECT.get()),
    NOTHING_CHANGED(
            Emoji.ALERT.get() + " Ҳеч нарсани ўзгартирмадингиз.",
            Emoji.ALERT.get() + " Вы ничего не изменили.",
            Emoji.ALERT.get() + " You haven't changed anything.");

    private final String uz;
    private final String ru;
    private final String en;

    public String lang(Language language) {
        switch (language) {
            case UZBEK -> {
                return this.uz;
            }
            case ENGLISH -> {
                return this.en;
            }
            case RUSSIAN -> {
                return this.ru;
            }
            default -> {
                return null;
            }
        }
    }
}
