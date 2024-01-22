package aromanticcat.umcproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class NangmanPostBoxResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendLetterResultDTO {

        private Long nangmanLetterId;

        private String senderNickname;

        LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LetterSummaryResultDTO{
        private Long nangmanLetterId;

        private String preview;

        private LocalDateTime createdAt;
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
    public static class SendReplyResultDTO {

        private Long nangmanLetterId;

        private Long nangmanReplyId;

        private String replySenderNickname;

        LocalDateTime createdAt;
    }
}
