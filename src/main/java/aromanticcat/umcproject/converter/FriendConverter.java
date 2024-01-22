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
                .friend_id(friend.getId())
                .friend_name(friend.getFriend_name())
                .are_we_friend(friend.isAre_we_friend())
                .are_we_close(friend.isAre_we_close())
                .build();
    }

}
