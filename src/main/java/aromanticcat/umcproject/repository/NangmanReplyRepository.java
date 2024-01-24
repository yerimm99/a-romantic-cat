package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.NangmanReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface NangmanReplyRepository extends JpaRepository<NangmanReply, Long> {

    boolean existsByMemberIdAndCreatedAtBetween(Long memberId, LocalDateTime start, LocalDateTime end);

    Optional<NangmanReply> findByMemberIdAndNangmanLetterId(Long memberId, Long nangmanLetterId);
}
