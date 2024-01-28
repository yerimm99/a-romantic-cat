package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.FriendStatus;
import aromanticcat.umcproject.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Page<Friend> findFriendByMemberAndFromIsTrueAndFriendStatus(Member member, Pageable pageable, FriendStatus status);

    Friend findFriendByMemberAndFriendName(Member member, String friendName);

}
