package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.web.dto.Member.MemberSignInRequestDto;
import aromanticcat.umcproject.web.dto.Member.MemberSignupRequestDto;
import aromanticcat.umcproject.web.dto.Member.TokenResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findByEmail(String email);

//    Member createUser(String email, String nickname);

//    void isNicknameExist(String nickname);

//    void isNicknameUnique(String nickname);

//    SecurityUserDto getUserInfo();

//    Member updateNickname(String nickname);

    Member findByMemberId(Long memberId, String userEmail);

    Long join(MemberSignupRequestDto requestDto);

    TokenResponseDto login(MemberSignInRequestDto requestDto);

    TokenResponseDto issueAccessToken(HttpServletRequest request);

}
