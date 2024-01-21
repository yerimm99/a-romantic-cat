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
}
