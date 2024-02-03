package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    Optional<Letter> findByLetterId(Long id);

    List<Letter> findById(Long id);
}
