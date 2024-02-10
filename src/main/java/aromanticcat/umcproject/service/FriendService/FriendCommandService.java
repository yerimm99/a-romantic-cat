package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.web.dto.Friend.FriendRequestDTO;

public interface FriendCommandService {

    void requestFriendship(String userEmail,Long toMemberLetterBoxId);

    void approveFriendship(String userEmail, Long friendId);

    void rejectFriendship(String userEmail, Long friendId);

    void setCloseFriend(String userEmail, Long friendId);
}
