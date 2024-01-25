package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Letterbox;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.LetterBoxRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendCommandServiceImpl implements FriendCommandService {

    private final MemberRepository memberRepository;
    private final LetterBoxRepository letterBoxRepository;

    @Override
    @Transactional
    public Member getMember(Long memberId) {

        Member fromMember = memberRepository.getMemberById(memberId);

        return fromMember;
    }

    @Override
    @Transactional
    public Member getMemberByLetterBoxId(Long toMemberLetterBoxId) {

        Optional<Letterbox> letterbox = letterBoxRepository.findById(toMemberLetterBoxId);

        Long toMemberId = letterbox.get().getMemberId();

        Member toMember = memberRepository.getMemberById(toMemberId);

        return toMember;
    }
}
