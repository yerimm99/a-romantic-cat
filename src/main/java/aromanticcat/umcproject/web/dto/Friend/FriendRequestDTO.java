package aromanticcat.umcproject.web.dto.Friend;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class FriendRequestDTO {

    @Getter
    @Builder
    public static class FriendshipRequestDTO{
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd", timezone = "/Asia/Seoul")
        LocalDate requestDate;
        Long fromMemberId;
        Long toMemberLetterBoxId;
    }
}
