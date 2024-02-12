package aromanticcat.umcproject.web.dto.Letterbox;

import aromanticcat.umcproject.entity.Letter;
import lombok.Getter;

@Getter
public class LetterRequest {
    private String nickname;
    private String content;
    private Boolean open;
    private Boolean loginStatus;
    private Long senderId;
    private Long stampId;
    private Long letterPaperId;
    private Long letterboxId;

    public Letter toEntity() {
        Letter letter = Letter.builder()
                .nickname(nickname)
                .content(content)
                .open(open)
                .loginStatus(loginStatus)
                .senderId(senderId)
                .build();
        return letter;
    }
}
