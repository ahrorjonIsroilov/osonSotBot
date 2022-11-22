package osonsot.entity.poster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.*;
import osonsot.entity.Auditable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Image extends Auditable {
  @Column(length = 500)
  private String path;

  private Float size;

  @Column(name = "file_id", length = 500)
  private String fileId;

  @ManyToOne
  @JoinColumn(name = "poster_id")
  private Poster poster;
}
