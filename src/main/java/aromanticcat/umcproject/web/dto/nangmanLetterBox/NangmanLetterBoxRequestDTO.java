package aromanticcat.umcproject.web.dto.nangmanLetterBox;

import lombok.Getter;

public class NangmanLetterBoxRequestDTO {

    @Getter
    public static class WriteLetterDTO {

        private Boolean isPublic;
        private String content;
        private String senderRandomNickname;
    }

    @Getter
    public static class WriteReplyDTO {

        private String replySenderNickname;
        private String replyContent;
    }
}
