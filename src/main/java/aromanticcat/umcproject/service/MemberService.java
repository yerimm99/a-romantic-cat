package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.security.SecurityUserDto;
import aromanticcat.umcproject.web.dto.Member.MemberRequestDTO;
import aromanticcat.umcproject.web.dto.Member.MemberResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findByEmail(String email);

    Member createUser(String email, String nickname);

    void isNicknameExist(String nickname);

    void isNicknameUnique(String nickname);

    SecurityUserDto getUserInfo();

    Member updateNickname(String nickname);

    Member findByMemberId(Long memberId);

}
