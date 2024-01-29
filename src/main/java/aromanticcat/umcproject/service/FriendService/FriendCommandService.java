package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.web.dto.Friend.FriendRequestDTO;

public interface FriendCommandService {

    void requestFriendship(FriendRequestDTO.FriendshipRequestDTO request);

    void approveFriendship(Long memberId, Long friendId);

    void rejectFriendship(Long memberId, Long friendId);
}
