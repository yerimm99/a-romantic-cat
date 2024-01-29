package aromanticcat.umcproject.web.dto.Friend;

import aromanticcat.umcproject.entity.FriendStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FriendResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FriendInfoDTO{      // 주소록, 친한친구에서 사용되는 DTO

        String friendName;      // 친구 닉네임
        Long friendLetterBoxId;     // 친구 우편함 번호
        FriendStatus friendStatus;  // 친구인지 여부
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WaitingFriendDTO{       // 보낸(받은) 요청에서 사용되는 DTO
        String friendName;      // 친구 닉네임
        Long friendLetterBoxId;     // 친구 우편함 번호
    }

}
