package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.converter.FriendConverter;
import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.FriendStatus;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.FriendRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.web.dto.Friend.FriendRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendCommandServiceImpl implements FriendCommandService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    public Member getMember(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. ID: " + email));
    }

    @Override
    @Transactional
    public void requestFriendship(String userEmail, Long toMemberId) {

        // 친구 요청을 보내는 사용자
        Member fromMember = getMember(userEmail);

        // 친구 요청을 받는 사용자
        Member toMember = memberRepository.findById(toMemberId).orElse(null);

        // 새로운 친구 객체 생성1 (fromMember 기준)
        Friend newFriend1 = FriendConverter.toFriend(fromMember, toMember, true);

        // 새로운 친구 객체 생성2 (toMember 기준)
        Friend newFriend2 = FriendConverter.toFriend(toMember, fromMember, false);

        // 각 사용자의 친구 리스트에 새로 만든 친구 객체 추가
        toMember.getFriends().add(newFriend1);
        fromMember.getFriends().add(newFriend2);

        // 각 객체의 변경 사항을 db에 반영
        friendRepository.save(newFriend1);
        friendRepository.save(newFriend2);

        newFriend1.setCounterpartId(newFriend2.getId());
        newFriend2.setCounterpartId(newFriend1.getId());
    }

    @Override
    @Transactional
    public void approveFriendship(String userEmail, Long friendId) {   // 친구 요청을 승인한 경우

        Member member = getMember(userEmail);

        Friend newFriend = friendRepository.findByMemberAndFriendId(member, friendId);
        Friend counterpart = friendRepository.findById(newFriend.getCounterpartId()).orElse(null);

        newFriend.changeFriendStatus(FriendStatus.APPROVED);
        counterpart.changeFriendStatus(FriendStatus.APPROVED);

    }

    @Override
    @Transactional
    public void rejectFriendship(String userEmail, Long friendId) {

        Member member = getMember(userEmail);

        Friend newFriend = friendRepository.findByMemberAndFriendId(member, friendId);
        Friend counterpart = friendRepository.findById(newFriend.getCounterpartId()).orElse(null);

        newFriend.changeFriendStatus(FriendStatus.REJECTED);
        counterpart.changeFriendStatus(FriendStatus.REJECTED);

    }

    @Override
    @Transactional
    public void setCloseFriend(String userEmail, Long friendId) {

        Member member = getMember(userEmail);

        Friend friend = friendRepository.findByMemberAndFriendId(member, friendId);

        friend.changeFriendStatus(FriendStatus.CLOSE_FRIEND);

    }

    @Override
    @Transactional
    public void deleteCloseFriend(String userEmail, Long friendId) {

        Member member = getMember(userEmail);

        Friend friend = friendRepository.findByMemberAndFriendId(member, friendId);

        friend.changeFriendStatus(FriendStatus.APPROVED);

    }
}
