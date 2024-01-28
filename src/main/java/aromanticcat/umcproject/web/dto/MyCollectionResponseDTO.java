package aromanticcat.umcproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MyCollectionResponseDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AcquiredLetterPaperResultDTO {

        private Long letterPaperId;

        private String letterPaperImageUrl;

        private String letterPaperName;

        private LocalDateTime acquiredAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AcquiredStampResultDTO {

        private Long stampId;

        private String stampImageUrl;

        private String stampName;

        private LocalDateTime acquiredAt;
    }

}
