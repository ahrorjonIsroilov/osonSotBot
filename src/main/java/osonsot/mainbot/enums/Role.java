package osonsot.mainbot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
  OWNER("owner"),
  ADMIN("admin"),
  USER("user");
  private final String code;
}
