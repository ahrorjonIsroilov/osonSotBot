package osonsot.entity.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
import osonsot.entity.Auditable;
import osonsot.enums.Districts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AuthUser extends Auditable {
    Long chatId;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "phone_number")
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Districts location;
    Integer score;
}
