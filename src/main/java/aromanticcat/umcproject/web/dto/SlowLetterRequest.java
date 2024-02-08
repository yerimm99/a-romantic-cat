package aromanticcat.umcproject.web.dto;

import aromanticcat.umcproject.entity.SlowLetter;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public record SlowLetterRequest(
        @NotNull LocalDate date,
        @NotNull String content,
        @NotNull Long memberId
) {

    public static SlowLetter toEntity(SlowLetterRequest request) {
        return SlowLetter.builder()
                .date(request.date)
                .content(request.content)
                .build();
    }
}