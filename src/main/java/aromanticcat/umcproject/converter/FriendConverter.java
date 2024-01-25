package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.web.dto.Friend.FriendResponseDTO;
import aromanticcat.umcproject.entity.Friend;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class FriendConverter {

    // Friend 엔티티 -> DTO 생성
    public static FriendResponseDTO.FriendDTO toFriendDTO(Friend friend){

        return FriendResponseDTO.FriendDTO.builder()
                .friendId(friend.getId())
                .friendName(friend.getFromMemberName())
                .isFriend(friend.isFriend())
                .isCloseFriend(friend.isCloseFriend())
                .build();
    }

}
