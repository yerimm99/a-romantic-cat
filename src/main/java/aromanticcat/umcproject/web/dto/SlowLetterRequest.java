package aromanticcat.umcproject.web.dto;

import aromanticcat.umcproject.entity.SlowLetter;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record SlowLetterRequest(
        @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
        @NotNull String content
) {

    public static SlowLetter toEntity(SlowLetterRequest request) {
        return SlowLetter.builder()
                .date(LocalDate.parse(request.date))
                .content(request.content)
                .build();
    }
}