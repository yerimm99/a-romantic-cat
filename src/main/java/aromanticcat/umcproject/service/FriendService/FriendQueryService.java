package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.web.dto.Friend.FriendResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface FriendQueryService {


    List<Friend> getFriendReceivedList(Long memberId);

    List<Friend> getFriendRequestedList(Long memberId);

    List<FriendResponseDTO.FriendInfoDTO> findFriendList(Long memberId, Integer page);

    List<FriendResponseDTO.FriendInfoDTO> findCloseFriendList(Long memberId, Integer page);

    List<FriendResponseDTO.FriendInfoDTO> getFriendbyFriendName(Long memberId, String friendName);

    List<FriendResponseDTO.FriendInfoDTO> getFriendbyFriendId(Long memberId, Long friendId);
}
