package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letter;
import aromanticcat.umcproject.entity.Letterbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    Optional<Letter> findByLetterId(Long id);

    Optional<List<Letter>> findLettersByLetterboxAndCreatedAtBefore(Letterbox letterbox, LocalDateTime now);

    Optional<List<Letter>> findLettersByLetterboxAndCreatedAtBeforeAndOpen(Letterbox letterbox, LocalDateTime now, Boolean open);
}
