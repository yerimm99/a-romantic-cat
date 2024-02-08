package aromanticcat.umcproject.web.dto;

import aromanticcat.umcproject.entity.SlowLetter;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public record SlowLetterResponse(
        @NotNull Long slowLetterId
) {
    public static SlowLetterResponse from(SlowLetter slowLetter) {
        return SlowLetterResponse.builder()
                .slowLetterId(slowLetter.getSlowLetterId())
                .build();
    }
}