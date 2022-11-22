package osonsot.entity.auth;

import org.springframework.stereotype.Component;
import osonsot.entity.poster.Image;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;

import java.util.*;

@Component
public class Session {

    private static final Map<Long, Optional<SessionUser>> sessions = new HashMap<>();
    private static final Session instance = new Session();

    public static Session getInstance() {
        if (instance != null) return instance;
        else return new Session();
    }

    public Optional<SessionUser> findByChatId(Long chatId) {
        if (Objects.nonNull(sessions.get(chatId))) return sessions.get(chatId);
        return Optional.empty();
    }

    public Boolean existsByChatId(Long chatId) {
        if (Objects.nonNull(sessions.get(chatId))) return sessions.get(chatId).isPresent();
        return false;
    }

    public Boolean existsByChatIdAndRegistered(Long chatId) {
        if (Objects.nonNull(sessions.get(chatId)))
            return sessions.get(chatId).isPresent() && sessions.get(chatId).get().getRegistered();
        return false;
    }

    public SessionUser getByChatId(Long chatId) {
        if (Objects.nonNull(sessions.get(chatId))) {
            Optional<SessionUser> sessionUser = sessions.get(chatId);
            return sessionUser.orElse(null);
        }
        return null;
    }

    public Role getRole(Long chatId) {
        if (Objects.nonNull(sessions.get(chatId))) {
            Optional<SessionUser> sessionUser = sessions.get(chatId);
            if (sessionUser.isPresent()) return sessionUser.get().getRole();
        }
        return null;
    }

    public Integer getPage(Long chatId) {
        if (Objects.nonNull(sessions.get(chatId))) {
            Optional<SessionUser> sessionUser = sessions.get(chatId);
            if (sessionUser.isPresent()) return sessionUser.get().getPage();
        }
        return null;
    }

    public Boolean checkState(State state, Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        return session.map(sessionUser -> sessionUser.getState().equals(state)).orElse(false);
    }

    public void setSession(Long chatId, Optional<SessionUser> session) {
        sessions.put(chatId, session);
    }

    public void setPhoto(Image photo, Long chatId) {
        Optional<SessionUser> sessionUser = findByChatId(chatId);
        if (sessionUser.isPresent()) {
            sessionUser.get().getPhotos().add(photo);
            setSession(chatId, sessionUser);
        }
    }

    public void setPhotos(List<Image> photos, Long chatId) {
        Optional<SessionUser> sessionUser = findByChatId(chatId);
        if (sessionUser.isPresent()) {
            sessionUser.get().setPhotos(photos);
            setSession(chatId, sessionUser);
        }
    }

    public List<Image> getPhotos(Long chatId) {
        Optional<SessionUser> user = findByChatId(chatId);
        return user.map(SessionUser::getPhotos).orElse(null);
    }

    public void clearPhotos(Long chatId) {
        Optional<SessionUser> sessionUser = findByChatId(chatId);
        sessionUser.ifPresent(user -> user.setPhotos(null));
    }

    public void setElement(SessionElement elementType, Object element, Long chatId) {
        Optional<SessionUser> sessionUser = findByChatId(chatId);
        if (sessionUser.isPresent()) {
            sessionUser.get().getElements().put(elementType, element);
            setSession(chatId, sessionUser);
        }
    }

    public <T> T getElement(Long chatId, SessionElement elementType) {
        Optional<SessionUser> session = findByChatId(chatId);
        return (T) session.map(s -> s.getElements().get(elementType)).orElse(null);
    }

    public Optional<SessionUser> prepare(AuthUser user) {
        return Optional.of(
                SessionUser.builder()
                        .state(State.DEFAULT)
                        .chatId(user.getChatId())
                        .page(user.getPage())
                        .role(user.getRole())
                        .langCode(user.getLanguage())
                        .registered(user.getRegistered())
                        .contact(user.getContact())
                        .location(user.getLocation())
                        .extraContact(user.getExtraContact())
                        .build());
    }

    public void setState(State state, Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        if (session.isPresent()) {
            session.get().setState(state);
            setSession(chatId, session);
        }
    }

    public State getState(Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        return session.map(SessionUser::getState).orElse(State.DEFAULT);
    }

    public void setContact(String phone, Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        if (session.isPresent()) {
            session.get().setContact(phone);
            setSession(chatId, session);
        }
    }

    public String getContact(Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        return session.map(SessionUser::getContact).orElse(null);
    }

    public void setCategoryId(Integer categoryId, Long chatId) {
        Optional<SessionUser> sessionUser = findByChatId(chatId);
        if (sessionUser.isPresent()) {
            sessionUser.get().getElements().put(SessionElement.CATEGORY_ID, categoryId);
            setSession(chatId, sessionUser);
        }
    }

    public Integer getCategoryId(Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        return (Integer) session.map(s -> s.getElements().get(SessionElement.CATEGORY_ID)).orElse(null);
    }

    public void setTempLong(Long temp, Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        if (session.isPresent()) {
            session.get().getElements().put(SessionElement.TEMP_LONG, temp);
            setSession(chatId, session);
        }
    }

    public Long getTempLong(Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        return (Long) session.map(s -> s.getElements().get(SessionElement.TEMP_LONG)).orElse(null);
    }

    public void setLang(Language language, Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        if (session.isPresent()) {
            session.get().setLangCode(language);
            setSession(chatId, session);
        }
    }

    public Language getLang(Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        return session.map(SessionUser::getLangCode).orElse(Language.UZBEK);
    }

    public void setLocation(District location, Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        if (session.isPresent()) {
            session.get().setLocation(location);
            setSession(chatId, session);
        }
    }

    public District getLocation(Long chatId) {
        Optional<SessionUser> session = findByChatId(chatId);
        return session.map(SessionUser::getLocation).orElse(null);
    }

    public void removeSession(Long chatId) {
        sessions.remove(chatId);
    }

    public void previousPage(Long chatId) {
        Optional<SessionUser> sessionUser = sessions.get(chatId);
        if (sessionUser.isPresent()) {
            SessionUser user = sessionUser.get();
            user.setPage(user.getPage() - 1);
            setSession(chatId, Optional.of(user));
        }
    }

    public void nextPage(Long chatId) {
        Optional<SessionUser> sessionUser = sessions.get(chatId);
        if (sessionUser.isPresent()) {
            SessionUser user = sessionUser.get();
            user.setPage(user.getPage() + 1);
            setSession(chatId, Optional.of(user));
        }
    }

    public void setPageZero(Long chatId) {
        Optional<SessionUser> user = sessions.get(chatId);
        if (user.isPresent()) {
            user.get().setPage(0);
            setSession(chatId, user);
        }
    }

    public Boolean checkRole(Long chatId, Role... role) {
        Role[] clone = role.clone();
        Optional<SessionUser> user = sessions.get(chatId);
        return Arrays.stream(clone).anyMatch(r -> user.map(u -> u.getRole().equals(r)).orElse(false));
    }
}
