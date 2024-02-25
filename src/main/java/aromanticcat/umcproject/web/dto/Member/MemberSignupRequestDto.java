package aromanticcat.umcproject.web.dto.Member;

import aromanticcat.umcproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignupRequestDto {
    private String email;
    private String nickname;
    private String password;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }
}
