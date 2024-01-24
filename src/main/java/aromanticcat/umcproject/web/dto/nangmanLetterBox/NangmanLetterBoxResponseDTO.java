package aromanticcat.umcproject.web.dto.nangmanLetterBox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class NangmanLetterBoxResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WriteLetterResultDTO {

        private Long nangmanLetterId;

        private String senderNickname;

        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PreviewLetterResultDTO {
        private Long nangmanLetterId;

        private String preview;

        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PreviewReplyResultDTO {
        private Long nangmanLetterId;

        private Boolean noReply;

        private Long nangmanReplyId;

        private String preview;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SelectedLetterResultDTO {
        private Long nangmanLetterId;
        private String senderNickname;
        private String replySenderNickname;
        private String nangmanLetterContent;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WriteReplyResultDTO {

        private Long nangmanLetterId;

        private Long nangmanReplyId;

        private String replySenderNickname;

        LocalDateTime createdAt;
    }
}
