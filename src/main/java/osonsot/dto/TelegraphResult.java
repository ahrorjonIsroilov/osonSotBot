package osonsot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TelegraphResult {
    private Boolean ok;
    private Result result;

    @Getter
    @Setter
    @AllArgsConstructor
    public class Result {
        private String path;
        private String url;
        private String title;
        private String description;
    }
}
