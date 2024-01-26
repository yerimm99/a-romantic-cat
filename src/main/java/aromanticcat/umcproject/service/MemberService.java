package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.security.SecurityUserDto;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findByEmail(String email);

    Member createUser(String email, String nickname);

    void isNicknameExist(String nickname);

    void isNicknameUnique(String nickname);

    public SecurityUserDto getUserInfo();

}
