package osonsot.entity.poster;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.*;
import osonsot.entity.Auditable;
import osonsot.mainbot.enums.localization.Language;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category extends Auditable {
  @Column(name = "name_uz")
  private String nameUz;

  @Column(name = "name_ru")
  private String nameRu;

  @Column(name = "name_en")
  private String nameEn;

  @Column(name = "parent_id")
  private Integer parentId;

  @Column(name = "is_subcategory")
  private Boolean isSubcategory;

  @Column(name = "is_final")
  private Boolean isFinal;

  public String getName(Language language) {
    switch (language) {
      case UZBEK -> {
        return this.nameUz;
      }
      case RUSSIAN -> {
        return this.nameRu;
      }
      default -> {
        return this.nameEn;
      }
    }
  }
}
