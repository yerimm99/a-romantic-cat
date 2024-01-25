package aromanticcat.umcproject.web.dto.Friend;

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
    public static class FriendDTO{
        Long friendId;
        String friendName;
        boolean isFriend;
        boolean isCloseFriend;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class friendshipRequestResultDTO { // 친구 추가 요청 완료 응답

        Long fromMemberId;  // 친구 추가를 보낸 사용자의 ID
        Long toMemberLetterBoxId;   // 친구 추가를 받은 사용자의 우편함 번호
        String toMemberNickname;    // 친구 추가를 받은 사용자의 이름
        LocalDateTime createdAt;    // 친구 추가를 보낸 시간
    }
}
