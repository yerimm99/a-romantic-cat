package aromanticcat.umcproject.service.FriendService;

import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FriendQueryService {

    Optional<Member> findMember(Long id);

    Page<Friend> getFriendList(Long memberId, Integer page);

    Page<Friend> getCloseFriendList(Long memberId, Integer page);

    Friend getFriend(Long memberId, String friendName);
}
