package aromanticcat.umcproject.web.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchMemberDTO{       // 친구 추가에서 사용자 찾기에 사용되는 DTO
        String nickname;      // 사용자 닉네임
        Long memberId;     // 사용자 우편함 번호
    }
}
