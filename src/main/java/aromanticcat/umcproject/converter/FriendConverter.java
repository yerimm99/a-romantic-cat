package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.entity.FriendStatus;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.repository.LetterBoxRepository;
import aromanticcat.umcproject.web.dto.Friend.FriendResponseDTO;
import aromanticcat.umcproject.entity.Friend;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class FriendConverter {

    // Friend 엔티티 -> DTO 생성
    public static FriendResponseDTO.FriendInfoDTO toFriendInfoDTO(Friend friend){

        return FriendResponseDTO.FriendInfoDTO.builder()
                .friendName(friend.getFriendName())
                .friendId(friend.getFriendId())
                .friendStatus(friend.getFriendStatus())
                .build();
    }

    public static FriendResponseDTO.WaitingFriendDTO toWaitingFriendDTO(Friend friend){

        return FriendResponseDTO.WaitingFriendDTO.builder()
                .friendName(friend.getFriendName())
                .friendId(friend.getFriendId())
                .build();
    }

    public static Friend toFriend(Member fromMember, Member toMember, boolean isSend){

        return Friend.builder()
                .friendName(toMember.getNickname())
                .friendId(toMember.getId())
                .member(fromMember)
                .fromMemberId(fromMember.getId())
                .fromMemberName(fromMember.getNickname())
                .toMemberId(toMember.getId())
                .toMemberName(toMember.getNickname())
                .friendStatus(FriendStatus.WAITING)
                .isFrom(isSend)   // true이면 fromMember가 false이면 toMember가 친구 요청을 보냄
                .build();
    }

}
