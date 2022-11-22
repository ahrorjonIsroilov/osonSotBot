package osonsot.entity.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import osonsot.entity.poster.Image;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;

@Getter
@Setter
@NoArgsConstructor
public class SessionUser {
  private Long chatId;
  private Role role;
  private State state;
  private Integer page;
  private String contact;
  private String extraContact;
  private Boolean registered;
  private Language langCode;
  private District location;
  private Map<SessionElement, Object> elements;
  private List<Image> photos;

  @Builder
  public SessionUser(
      Long chatId,
      Role role,
      State state,
      Integer page,
      String contact,
      String extraContact,
      Boolean registered,
      Language langCode,
      District location) {
    this.chatId = chatId;
    this.role = role;
    this.state = state;
    this.page = page;
    this.contact = contact;
    this.extraContact = extraContact;
    this.registered = registered;
    this.langCode = langCode;
    this.location = location;
    this.elements = new HashMap<>();
    this.photos = new ArrayList<>();
  }
}
