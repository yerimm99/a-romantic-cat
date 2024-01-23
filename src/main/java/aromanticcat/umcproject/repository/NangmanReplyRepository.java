package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.NangmanReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface NangmanReplyRepository extends JpaRepository<NangmanReply, Long> {

    boolean existsByMemberIdAndCreatedAtBetween(Long memberId, LocalDateTime start, LocalDateTime end);
}
