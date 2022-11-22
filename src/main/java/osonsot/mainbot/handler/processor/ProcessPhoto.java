package osonsot.mainbot.handler.processor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import osonsot.base.handler.Processor;
import osonsot.entity.auth.SessionUser;
import osonsot.entity.poster.Image;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.localization.Language;
import osonsot.mainbot.enums.localization.Words;
import osonsot.service.BotService;
import osonsot.util.Utils;

import java.util.List;

@Component
public class ProcessPhoto extends Processor {
    public ProcessPhoto(BotService service) {
        super(service);
    }

    @Override
    public void handle(Update update) {
        SessionUser sessionUser = this.session.getByChatId(chatId);
        Language lang = this.session.getLang(chatId);
        switch (sessionUser.getRole()) {
            case USER -> {
                switch (session.getState(chatId)) {
                    case SUBMIT_PHOTO, EDIT_POSTER_PHOTO -> {
                        Runnable runnable = () -> {
                            if (update.getMessage().getMediaGroupId() == null) {
                                if (session.getPhotos(chatId) != null && session.getPhotos(chatId).size() > 4) {
                                    sendMessage(Words.MAXIMUM_PHOTO_LIMIT.lang(lang), Formatting.BOLD);
                                    return;
                                }
                                List<PhotoSize> photo = update.getMessage().getPhoto();
                                PhotoSize photoSize = photo.get(2) != null ? photo.get(2) : photo.get(0);
                                if (photoSize.getFileSize() / 1000000F > 3F) {
                                    sendMessage(Words.MAXIMUM_PHOTO_SIZE.lang(lang), Formatting.BOLD);
                                    return;
                                }
                                sendMessage(Words.SUBMIT_OR_DONE.lang(lang), Formatting.BOLD);
                                session.setPhoto(
                                        Image.builder()
                                                .fileId(photoSize.getFileId())
                                                .path(Utils.uploadTelegraph(downloadFile(photoSize.getFileId())))
                                                .size(photoSize.getFileSize() / 1000F)
                                                .build(),
                                        chatId);
                            }
                        };
                        thread.submit(runnable);
                    }
                }
            }
            case ADMIN, OWNER -> {
            }
        }
    }
}
