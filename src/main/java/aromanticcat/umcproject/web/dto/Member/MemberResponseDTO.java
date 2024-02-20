package aromanticcat.umcproject.web.dto.Member;

import aromanticcat.umcproject.entity.FriendStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberJoinResultDTO {
        private String nickname;

        private String email;

        private int coin;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberNicknameDTO {
        String nickname;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberInfoDTO{

        String Name;        // 멤버 닉네임
        Long MemberId;      // 멤버 우편함 번호 (고유 번호)
    }
}
