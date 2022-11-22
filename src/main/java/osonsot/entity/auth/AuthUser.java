package osonsot.entity.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
import osonsot.entity.Auditable;
import osonsot.mainbot.enums.District;
import osonsot.mainbot.enums.Role;
import osonsot.mainbot.enums.State;
import osonsot.mainbot.enums.localization.Language;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AuthUser extends Auditable {
    Long chatId;

    @Column(name = "contact")
    String contact;

    @Column(name = "extra_contact")
    String extraContact;

    String username;

    @Enumerated(EnumType.STRING)
    District location;

    Boolean registered;
    Boolean notification;
    Boolean blocked;

    @Column(name = "blocked_until")
    LocalDateTime blockedUntil;

    @Column(name = "blocked_count")
    Integer blockedCount;

    @Enumerated(EnumType.STRING)
    State state;

    Language language;
    Integer page;

    @Enumerated(EnumType.STRING)
    Role role;

    Integer score;
}
