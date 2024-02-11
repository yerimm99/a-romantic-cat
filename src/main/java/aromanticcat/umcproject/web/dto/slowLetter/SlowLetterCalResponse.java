package aromanticcat.umcproject.web.dto.slowLetter;

import aromanticcat.umcproject.entity.SlowLetter;
import lombok.Builder;

@Builder
public record SlowLetterCalResponse(
        String date
) {
    public static SlowLetterCalResponse from(SlowLetter slowLetter) {
        return SlowLetterCalResponse.builder()
                .date(slowLetter.getDate().toString())
                .build();
    }
}