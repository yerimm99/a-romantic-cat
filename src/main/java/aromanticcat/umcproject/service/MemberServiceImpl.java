package aromanticcat.umcproject.service;

import aromanticcat.umcproject.apiPayload.code.status.ErrorStatus;
import aromanticcat.umcproject.apiPayload.exception.handler.MemberHandler;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.security.Role;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {


    private final MemberRepository repository;

    @Override
    public Optional<Member> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Member createUser(String email, String nickname) {
        // 닉네임 입력 체크
        isNicknameExist(nickname);
        // 닉네임 중복 체크
        isNicknameUnique(nickname);

        Member newUser = Member.builder()
                .email(email)
                .nickname(nickname)
                .userRole(Role.USER)
                .build();

        return repository.save(newUser);
    }

    @Override
    public void isNicknameExist(String nickname) {
        if (nickname.isEmpty()) {
            throw new MemberHandler(ErrorStatus.NICKNAME_NOT_EXIST);
        }
    }

    @Override
    public void isNicknameUnique(String nickname) {
        Optional<Member> existingMember = repository.findByNickname(nickname);
        if (existingMember.isPresent()) {
            throw new MemberHandler(ErrorStatus.NICKNAME_ALREADY_EXIST);
        }
    }


}
