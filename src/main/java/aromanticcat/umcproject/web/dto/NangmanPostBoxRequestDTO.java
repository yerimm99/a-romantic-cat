package aromanticcat.umcproject.web.dto;

import lombok.Getter;

public class NangmanPostBoxRequestDTO {

    @Getter
    public static class SendLetterDTO {

        private Boolean isPublic;
        private String content;
        private String senderRandomNickname;
        private Long memberId;
    }

    @Getter
    public static class ReplyLetterDTO {

        private String replySenderNickname;
        private String replyContent;
        private Long nangmanLetterId;
        private Long memberId;
    }
}
