package osonsot.base.command;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import osonsot.base.Bot;
import osonsot.button.InlineButton;
import osonsot.entity.auth.Session;
import osonsot.entity.auth.SessionUser;
import osonsot.entity.poster.Image;
import osonsot.entity.poster.Poster;
import osonsot.mainbot.enums.Formatting;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;
import osonsot.service.BotService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public abstract class Command {

    public static Bot bot;
    protected final BotService service;
    protected final Session session = Session.getInstance();
    protected Long chatId;
    protected Language lang;
    protected ExecutorService thread;
    protected ScheduledExecutorService scheduledThread;

    protected Command(BotService service) {
        this.service = service;
    }

    protected boolean beforeHandle(Update update) {
        chatId = getMessage(update).getChatId();
        thread = Executors.newFixedThreadPool(4);
        scheduledThread = Executors.newScheduledThreadPool(4);
        if (!session.existsByChatIdAndRegistered(chatId)) {
            if (!service.existsByChatId(chatId)) {
                session.setSession(
                        chatId,
                        Optional.of(
                                SessionUser.builder()
                                        .role(Role.USER)
                                        .chatId(chatId)
                                        .state(State.SELECT_LANG)
                                        .langCode(Language.DEFAULT)
                                        .registered(true)
                                        .page(0)
                                        .build()));
                sendMessage(
                        "Тилни танланг\nSelect a language\nВыберите язык",
                        InlineButton.chooseLang(session.getByChatId(chatId)),
                        Formatting.BOLD);
                return false;
            }
            session.setSession(chatId, session.prepare(service.getUserByChatId(chatId)));
        }
        lang = session.getLang(chatId);
        return true;
    }

    protected void sendMessage(String text, Formatting formatting) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.enableHtml(true);
        text = formatText(text, formatting);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendMessage(String text, ReplyKeyboardMarkup markup, Formatting formatting) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.enableHtml(true);
        message.setReplyMarkup(markup);
        text = formatText(text, formatting);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendMessage(String text, InlineKeyboardMarkup markup, Formatting formatting) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.enableHtml(true);
        message.setReplyMarkup(markup);
        text = formatText(text, formatting);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendMessage(
            String text, Long anotherChatId, InlineKeyboardMarkup markup, Formatting formatting) {
        SendMessage message = new SendMessage();
        message.setChatId(anotherChatId);
        message.enableHtml(true);
        message.setReplyMarkup(markup);
        text = formatText(text, formatting);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendMessage(
            String text, ReplyKeyboardRemove keyboardRemove, Formatting formatting) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.enableHtml(true);
        message.setReplyMarkup(keyboardRemove);
        text = formatText(text, formatting);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendMessage(String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendMessage(String text, ReplyKeyboardMarkup markup) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyMarkup(markup);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void sendMessage(String text, InlineKeyboardMarkup markup) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyMarkup(markup);
        try {
            bot.execute(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void editMessage(
            Update update, String text, InlineKeyboardMarkup markup, Formatting formatting) {
        Integer messageId = getMessage(update).getMessageId();
        text = formatText(text, formatting);
        EditMessageText editMessage = new EditMessageText(text);
        editMessage.setMessageId(messageId);
        editMessage.setChatId(chatId);
        editMessage.enableHtml(true);
        editMessage.setReplyMarkup(markup);
        try {
            bot.execute(editMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void editMessage(Update update, String text, Formatting formatting) {
        Integer messageId = getMessage(update).getMessageId();
        text = formatText(text, formatting);
        EditMessageText editMessage = new EditMessageText(text);
        editMessage.setMessageId(messageId);
        editMessage.setChatId(chatId);
        editMessage.enableHtml(true);
        try {
            bot.execute(editMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void editMedia(Update update, Poster poster, String text, InlineKeyboardMarkup markup) {
        Integer messageId = getMessage(update).getMessageId();
        text = formatText(text, Formatting.BOLD);
        InputMediaPhoto photo = new InputMediaPhoto();
        photo.setMedia(poster.getImages().get(0).getFileId());
        photo.setCaption(text);
        photo.setParseMode("HTML");
        EditMessageMedia editMessage = new EditMessageMedia();
        editMessage.setChatId(chatId);
        editMessage.setMessageId(messageId);
        editMessage.setReplyMarkup(markup);
        editMessage.setMedia(photo);
        try {
            bot.execute(editMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void editMessageCaption(Update update, String text, InlineKeyboardMarkup markup) {
        Integer messageId = getMessage(update).getMessageId();
        text = formatText(text, Formatting.BOLD);
        EditMessageCaption editMessage = new EditMessageCaption();
        editMessage.setMessageId(messageId);
        editMessage.setChatId(chatId);
        editMessage.setCaption(text);
        editMessage.setReplyMarkup(markup);
        editMessage.setParseMode("HTML");
        try {
            bot.execute(editMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void editMessage(Update update, String text, InlineKeyboardMarkup markup) {
        Integer messageId = getMessage(update).getMessageId();
        EditMessageText editMessage = new EditMessageText(text);
        editMessage.setMessageId(messageId);
        editMessage.setChatId(chatId);
        editMessage.setEntities(getMessage(update).getEntities());
        editMessage.setReplyMarkup(markup);
        try {
            bot.execute(editMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void editMessage(Update update, String text) {
        Integer messageId = getMessage(update).getMessageId();
        EditMessageText editMessage = new EditMessageText(text);
        editMessage.setMessageId(messageId);
        editMessage.setChatId(chatId);
        editMessage.setEntities(getMessage(update).getEntities());
        try {
            bot.execute(editMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void editMessage(Update update, InlineKeyboardMarkup markup) {
        Integer messageId = getMessage(update).getMessageId();
        EditMessageText editMessage = new EditMessageText(getMessage(update).getText());
        editMessage.setMessageId(messageId);
        editMessage.setChatId(chatId);
        editMessage.setEntities(getMessage(update).getEntities());
        editMessage.setReplyMarkup(markup);
        try {
            bot.execute(editMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendSticker(String fileId, ReplyKeyboardRemove markup) {
        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(chatId);
        sendSticker.setReplyMarkup(markup);
        sendSticker.setSticker(new InputFile(fileId));
        try {
            bot.execute(sendSticker);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendPopup(String text, String callbackQueryId) {
        AnswerCallbackQuery query = new AnswerCallbackQuery();
        query.setText(text);
        query.setCallbackQueryId(callbackQueryId);
        try {
            bot.execute(query);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendPopup(String text, String callbackQueryId, Boolean showAlert) {
        AnswerCallbackQuery query = new AnswerCallbackQuery();
        query.setText(text);
        query.setCallbackQueryId(callbackQueryId);
        query.setShowAlert(showAlert);
        try {
            bot.execute(query);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendMediaGroupPhoto(List<Image> images, Long chatId) {
        SendMediaGroup mediaGroup = new SendMediaGroup();
        List<InputMedia> mediaList = new ArrayList<>();
        for (Image image : images) {
            mediaList.add(InputMediaPhoto.builder().media(image.getFileId()).build());
        }
        mediaGroup.setMedias(mediaList);
    }

    protected void sendPhoto(
            Poster poster, String text, InlineKeyboardMarkup markup, Formatting formatting) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(poster.getImages().get(0).getFileId()));
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(formatText(text, formatting));
        sendPhoto.setParseMode("HTML");
        sendPhoto.setReplyMarkup(markup);
        sendPhoto.setProtectContent(true);
        try {
            bot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void sendMediaGroupPhoto(Poster poster, Formatting formatting) {
        SendMediaGroup mediaGroup = new SendMediaGroup();
        List<InputMedia> mediaList = new ArrayList<>();
        for (Image image : poster.getImages()) {
            mediaList.add(InputMediaPhoto.builder().media(image.getFileId()).build());
        }
        InputMedia media = mediaList.get(mediaList.size() - 1);
        media.setParseMode("HTML");
        media.setCaption(formatText(poster.getDescription(), formatting));
        mediaGroup.setMedias(mediaList);
        mediaGroup.setChatId(chatId);
        mediaGroup.setProtectContent(true);
        try {
            bot.execute(mediaGroup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    protected void deleteMessage(Update update) {
        try {
            bot.execute(new DeleteMessage(chatId.toString(), getMessage(update).getMessageId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private Message getMessage(Update update) {
        if (update.hasMessage()) return update.getMessage();
        else if (update.hasCallbackQuery()) return update.getCallbackQuery().getMessage();
        return new Message();
    }

    private String formatText(String text, Formatting formatting) {
        switch (formatting) {
            case BOLD -> text = "<b>" + text + "</b>";
            case ITALIC -> text = "<i>" + text + "</i>";
            case STRIKE -> text = "<s>" + text + "</s>";
            case UNDERLINE -> text = "<u>" + text + "</u>";
            case SPOILER -> text = "<span class=\"tg-spoiler\">" + text + "</span>";
            case MONOSPACE -> text = "<code>" + text + "</code>";
        }
        return text;
    }

    protected String getFilePath(String fileId) {
        GetFile getFile = new GetFile();
        getFile.setFileId(fileId);
        File file = new File();
        try {
            file = bot.execute(getFile);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return file.getFilePath();
    }

    protected java.io.File downloadFile(String fileId) {
        java.io.File file = new java.io.File(fileId + ".jpg");
        try {
            GetFile getFile = new GetFile(fileId);
            File execute = bot.execute(getFile);
            file = bot.downloadFile(execute, file);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void setBot(Bot staticBot) {
        Command.bot = staticBot;
    }
}
