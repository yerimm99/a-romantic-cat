package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.web.dto.Friend.FriendRequestDTO;

public interface FriendCommandService {

    void requestFriendship(Long memberId,Long toMemberLetterBoxId);

    void approveFriendship(Long memberId, Long friendId);

    void rejectFriendship(Long memberId, Long friendId);

    void setCloseFriend(Long memberId, Long friendId);
}
