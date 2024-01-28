package aromanticcat.umcproject.converter;

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
                .friendLetterBoxId(friend.getFriendLetterboxId())
                .friendStatus(friend.getFriendStatus())
                .build();
    }

    public static FriendResponseDTO.WaitingFriendDTO toWaitingFriendDTO(Friend friend){

        return FriendResponseDTO.WaitingFriendDTO.builder()
                .friendName(friend.getFriendName())
                .friendLetterBoxId(friend.getFriendLetterboxId())
                .build();
    }

}
