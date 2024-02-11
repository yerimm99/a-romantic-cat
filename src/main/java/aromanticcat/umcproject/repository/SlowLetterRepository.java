package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letter;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.SlowLetter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SlowLetterRepository extends JpaRepository<SlowLetter, Long> {
    Optional<List<SlowLetter>> findSlowLettersByMember(Member member);
    Optional<SlowLetter> findBySlowLetterId(Long slowLetterId);

    Optional<List<SlowLetter>> findSlowLettersByDateBetweenAndMemberId(LocalDate startDate, LocalDate endDate, Long memberId);
}
