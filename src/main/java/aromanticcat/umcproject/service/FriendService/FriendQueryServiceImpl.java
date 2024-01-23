package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.FriendRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FriendQueryServiceImpl implements FriendQueryService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    @Override
    public Optional<Member> findMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member;
    }

    @Override
    public Page<Friend> getFriendList(Long memberId, Integer page) {

        // page는 페이지의 번호, 12는 한 페이지에 보여줄 친구의 수
        Pageable pageable = PageRequest.of(page,12);

        Member member = memberRepository.findById(memberId).get();

        Page<Friend> friendList = friendRepository.findFriendByMember(member, pageable);

        return friendList;
    }

    @Override
    public Page<Friend> getCloseFriendList(Long memberId, Integer page) {

        // page는 페이지의 번호, 12는 한 페이지에 보여줄 친구의 수
        Pageable pageable = PageRequest.of(page,12);

        Member member = memberRepository.findById(memberId).get();

        Page<Friend> closeFriendList = friendRepository.findFriendByMemberAndCloseFriend(member, pageable);

        return closeFriendList;
    }
}
