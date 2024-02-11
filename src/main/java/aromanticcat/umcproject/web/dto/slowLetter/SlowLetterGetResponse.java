package aromanticcat.umcproject.web.dto.slowLetter;

import aromanticcat.umcproject.entity.SlowLetter;
import lombok.Builder;

@Builder
public record SlowLetterGetResponse(
        Long slowLetterId,
        String date,
        String content
) {
    public static SlowLetterGetResponse from(SlowLetter slowLetter) {
        return SlowLetterGetResponse.builder()
                .slowLetterId(slowLetter.getSlowLetterId())
                .date(slowLetter.getDate().toString())
                .content(slowLetter.getContent())
                .build();
    }
}