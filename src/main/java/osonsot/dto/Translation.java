package osonsot.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Translation {
  private Integer code;
  private String lang;
  private List<String> text;

  public String translation() {
    StringBuilder builder = new StringBuilder();
    for (String word : this.text) {
      builder.append(word).append(" ");
    }
    return builder.toString();
  }
}
