package aromanticcat.umcproject.web.dto.Member;

import aromanticcat.umcproject.entity.Member;
import lombok.Getter;

@Getter
public class MemberSignupResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private String password;

    public MemberSignupResponseDto(Member member){
        this.id = member.getId();;
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.password = member.getPassword();
    }
}
