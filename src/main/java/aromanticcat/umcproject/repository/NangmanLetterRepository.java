package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.NangmanLetter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NangmanLetterRepository extends JpaRepository<NangmanLetter, Long> {

    List<NangmanLetter> findByHasResponseFalse();

}
