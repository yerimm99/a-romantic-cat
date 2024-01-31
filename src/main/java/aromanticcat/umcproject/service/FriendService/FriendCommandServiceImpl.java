package aromanticcat.umcproject.service.FriendService;

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

    @Override
    @Transactional
    public void requestFriendship(Long memberId, Long toMemberId) {

        // 친구 요청을 보내는 사용자
        Member fromMember = memberRepository.findById(memberId).orElse(null);

        // 친구 요청을 받는 사용자
        Member toMember = memberRepository.findById(toMemberId).orElse(null);

        // dto의 정보를 기반으로 새로운 친구 객체 생성1 (fromMember 기준)
        Friend newFriend1 = Friend.builder()
                .friendName(toMember.getNickname())
                .friendId(toMemberId)
                .member(fromMember)
                .fromMemberId(fromMember.getId())
                .fromMemberName(fromMember.getNickname())
                .toMemberId(toMember.getId())
                .toMemberName(toMember.getNickname())
                .friendStatus(FriendStatus.WAITING)
                .isFrom(true)   // true이면 fromMember가 친구 요청을 보냄
                .build();

        // dto의 정보를 기반으로 새로운 친구 객체 생성2 (toMember 기준)
        Friend newFriend2 = Friend.builder()
                .friendName(fromMember.getNickname())
                .friendId(fromMember.getId())
                .member(toMember)
                .fromMemberId(toMember.getId())
                .fromMemberName(toMember.getNickname())
                .toMemberId(fromMember.getId())
                .toMemberName(fromMember.getNickname())
                .friendStatus(FriendStatus.WAITING)
                .isFrom(false)  // false이면 toMember가 친구 요청을 보냄
                .build();

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
    public void approveFriendship(Long memberId, Long friendId) {   // 친구 요청을 승인한 경우

        Friend newFriend = friendRepository.findByMemberIdAndFriendId(memberId, friendId);
        Friend counterpart = friendRepository.findById(newFriend.getCounterpartId()).orElse(null);

        newFriend.changeFriendStatus(FriendStatus.APPROVED);
        counterpart.changeFriendStatus(FriendStatus.APPROVED);

    }

    @Override
    @Transactional
    public void rejectFriendship(Long memberId, Long friendId) {

        Friend newFriend = friendRepository.findByMemberIdAndFriendId(memberId, friendId);
        Friend counterpart = friendRepository.findById(newFriend.getCounterpartId()).orElse(null);

        newFriend.changeFriendStatus(FriendStatus.REJECTED);
        counterpart.changeFriendStatus(FriendStatus.REJECTED);

    }

    @Override
    @Transactional
    public void setCloseFriend(Long memberId, Long friendId) {

        Friend friend = friendRepository.findByMemberIdAndFriendId(memberId, friendId);

        friend.changeFriendStatus(FriendStatus.CLOSE_FRIEND);

    }
}
