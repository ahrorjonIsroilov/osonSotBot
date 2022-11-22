package osonsot.mainbot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum State {
    DEFAULT("default"),
    SETTINGS("settings"),
    CATEGORIES("categories"),
    SELECT_LANG("sel_lang"),
    SHARE_CONTACT("share_cont"),
    ADD_CONTACT("add_cont"),
    ADD_CATEGORY("add_cat"),
    ADD_SUB_CATEGORY("add_sub_cat"),
    EDIT_CONTACT("edit_cont"),
    EDIT_CATEGORY("edit_cat"),
    EDIT_CONTACT_PANEL("edit_cont_pan"),
    CHOOSE_CATEGORY("choose_cat"),
    CHOOSE_REGION("choose_reg"),
    WRITE_DESCRIPTION("write_desc"),
    SEND_PRICE("send_price"),
    SUBMIT_PHOTO("submit_photo"),
    SUBMIT_POSTER_NAME("submit_poster_name"),
    INPUT_NEW_POSTER_TITLE("input_new_title"),
    INPUT_NEW_POSTER_DESCRIPTION("input_new_desc"),
    INPUT_NEW_POSTER_PRICE("input_new_price"),
    SELECT_NEW_CATEGORY("select_new_category"),
    EDIT_POSTER_PHOTO("edit-poster-photo");
    private final String code;
}
