package aromanticcat.umcproject.web.dto.Letterbox;

import lombok.Data;

@Data
public class LetterResponse {
    private String nickname;
    private String content;
    private Boolean open;
    private Boolean loginStatus;
    private Long senderId;
    private Long stampId;
    private Long letterPaperId;
    private Long letterboxId;
}
