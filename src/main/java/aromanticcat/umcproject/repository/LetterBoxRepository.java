package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letter;
import aromanticcat.umcproject.entity.Letterbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LetterBoxRepository extends JpaRepository<Letterbox, Long> {
    Letterbox findByMemberId(Long memberId);

    Optional<Letterbox> findByMemberIdAndActivate(Long memberId, boolean activate);
    Optional<Letterbox> findById(Long id);

    Optional<List<Letterbox>> findByMemberIdAndEndDtBefore(Long memberId, LocalDateTime currentTime);
}
