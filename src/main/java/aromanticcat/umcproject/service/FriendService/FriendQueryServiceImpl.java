package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.FriendStatus;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.FriendRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendQueryServiceImpl implements FriendQueryService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    @Override
    @Transactional
    public Optional<Member> findMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member;
    }

    @Override
    @Transactional
    public Page<Friend> getFriendList(Long memberId, Integer page) {    // 주소록에 있는 친구 정보들 조회 API

        // page는 페이지의 번호, 12는 한 페이지에 보여줄 친구의 수
        Pageable pageable = PageRequest.of(page,12);

        Member member = memberRepository.findById(memberId).get();

        Page<Friend> friendList = friendRepository.findFriendByMemberAndFromIsTrueAndFriendStatus(member, pageable, FriendStatus.APPROVED);

        return friendList;
    }

    @Override
    @Transactional
    public Page<Friend> getCloseFriendList(Long memberId, Integer page) {

        // page는 페이지의 번호, 12는 한 페이지에 보여줄 친구의 수
        Pageable pageable = PageRequest.of(page,12);

        Member member = memberRepository.findById(memberId).get();

        Page<Friend> closeFriendList = friendRepository.findFriendByMemberAndFromIsTrueAndFriendStatus(member, pageable, FriendStatus.CLOSE_FRIEND);

        return closeFriendList;
    }

    @Override
    @Transactional
    public Friend getFriend(Long memberId, String friendName) {

        Member member = memberRepository.findById(memberId).get();

        Friend friend = friendRepository.findFriendByMemberAndFriendName(member, friendName);

        return friend;
    }

    @Override
    @Transactional
    public List<Friend> getFriendReceivedList(Long memberId) {      // 요청 받은 친구 정보만 가져오기

        // 해당 API를 요청한 사용자 객체를 가져온 뒤 해당 객체의 친구 리스트를 가져옴
        Member member = memberRepository.findById(memberId).orElse(null);
        List<Friend> friendList = member.getFriends();
        List<Friend> results = new ArrayList<>();

        // 각 친구 요청 정보들을 확인하면서 조건에 부합하는 친구 정보를 results에 추가
        for(Friend friend: friendList){
            if(!friend.isFrom() && friend.getFriendStatus() == FriendStatus.WAITING){
                results.add(friend);
            }
        }

        return results;
    }

    @Override
    @Transactional
    public List<Friend> getFriendRequestedList(Long memberId) {

        // 해당 API를 요청한 사용자 객체를 가져온 뒤 해당 객체의 친구 리스트를 가져옴
        Member member = memberRepository.findById(memberId).orElse(null);
        List<Friend> friendList = member.getFriends();
        List<Friend> results = new ArrayList<>();

        // 각 친구 요청 정보들을 확인하면서 조건에 부합하는 친구 정보를 results에 추가
        for(Friend friend: friendList){
            if(friend.isFrom() && friend.getFriendStatus() == FriendStatus.WAITING){
                results.add(friend);
            }
        }

        return results;
    }

}
