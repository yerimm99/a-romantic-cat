package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.converter.FriendConverter;
import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.FriendStatus;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.FriendRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.web.dto.Friend.FriendResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendQueryServiceImpl implements FriendQueryService {

    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;

    public Member getMember(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. ID: " + email));
    }

    @Override
    @Transactional
    public List<Friend> getFriendReceivedList(String userEmail) {      // 요청 받은 친구 정보만 가져오기

        // 해당 API를 요청한 사용자 객체를 가져온 뒤 해당 객체의 친구 리스트를 가져옴
        Member member = getMember(userEmail);
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
    public List<Friend> getFriendRequestedList(String userEmail) {

        // 해당 API를 요청한 사용자 객체를 가져온 뒤 해당 객체의 친구 리스트를 가져옴
        Member member = getMember(userEmail);
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

    @Override
    @Transactional
    public List<FriendResponseDTO.FriendInfoDTO> findFriendList(String userEmail, Integer page) {

        Member member = getMember(userEmail);

        // page는 페이지의 번호, 12는 한 페이지에 보여줄 친구의 수
        Pageable pageable = PageRequest.of(page,12);

        // 친구와 친한 친구를 모두 조회하기 위함
        Set<FriendStatus> friendStatus = new HashSet<>();
        friendStatus.add(FriendStatus.APPROVED);
        friendStatus.add(FriendStatus.CLOSE_FRIEND);

        Page<Friend> friendPage = friendRepository.findFriendByMemberAndFriendStatus(member, pageable, friendStatus);

        List<Friend> friendList = friendPage.getContent();

        List<FriendResponseDTO.FriendInfoDTO> friendInfoDTOList = friendList.stream()
                .map(FriendConverter::toFriendInfoDTO)
                .collect(Collectors.toList());

        return  friendInfoDTOList;

    }

    @Override
    @Transactional
    public List<FriendResponseDTO.FriendInfoDTO> findCloseFriendList(String userEmail, Integer page) {

        Member member = getMember(userEmail);

        // page는 페이지의 번호, 12는 한 페이지에 보여줄 친구의 수
        Pageable pageable = PageRequest.of(page,12);

        Page<Friend> friendPage = friendRepository.findFriendByMemberAndFriendStatus(member, pageable, FriendStatus.CLOSE_FRIEND);

        List<Friend> friendList = friendPage.getContent();

        List<FriendResponseDTO.FriendInfoDTO> friendInfoDTOList = friendList.stream()
                .map(FriendConverter::toFriendInfoDTO)
                .collect(Collectors.toList());

        return  friendInfoDTOList;

    }

    @Override
    public List<FriendResponseDTO.FriendInfoDTO> getFriendbyFriendName(String userEmail, String friendName) {  // 중복된 이름이 있을 수 있으므로 List 반환

        Member member = getMember(userEmail);

        List<Friend> friendList = friendRepository.findFriendByMemberAndFriendName(member, friendName);

        List<FriendResponseDTO.FriendInfoDTO> friendInfoDTOList = friendList.stream()
                .map(FriendConverter::toFriendInfoDTO)
                .collect(Collectors.toList());

        return  friendInfoDTOList;

    }

    @Override
    public List<FriendResponseDTO.FriendInfoDTO> getFriendbyFriendId(String userEmail, Long friendId) {

        Member member = getMember(userEmail);

        List<Friend> friendList = friendRepository.findFriendByMemberAndFriendId(member, friendId);

        List<FriendResponseDTO.FriendInfoDTO> friendInfoDTOList = friendList.stream()
                .map(FriendConverter::toFriendInfoDTO)
                .collect(Collectors.toList());

        return  friendInfoDTOList;
    }

}
