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
}
