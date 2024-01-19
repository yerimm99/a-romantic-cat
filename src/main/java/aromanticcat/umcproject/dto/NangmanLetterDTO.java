package aromanticcat.umcproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NangmanLetterDTO {

    @NotNull
    private Long id;

    @NotNull(message = "sender_nickname은 null일 수 없습니다.")
    private String sender_nickname;

    @NotNull(message = "is_public는 null일 수 없습니다.")
    private Boolean is_public;

    @NotNull(message = "content는 null일 수 없습니다.")
    private String content;

    @Builder.Default
    private Boolean has_response = false;

    private Integer thumbs_up_cnt;

    private Integer heart_cnt;

    private Integer crying_cnt;

    private Integer clover_cnt;

    private Integer clap_cnt;

    private Integer star_cnt;

    @NotNull(message = "member는 null일 수 없습니다.")
    private Long member_id;
}
