package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letter;
import aromanticcat.umcproject.entity.SlowLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlowLetterRepository extends JpaRepository<SlowLetter, Long> {
}
