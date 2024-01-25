package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Member;

public interface FriendCommandService {

    Member getMember(Long memberId);

    Member getMemberByLetterBoxId(Long toMemberLetterBoxId);
}
