package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.NangmanLetter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NangmanLetterRepository extends JpaRepository<NangmanLetter, Long> {

    Page<NangmanLetter> findByHasResponseFalseAndMemberIdNot(Long memerId, Pageable pageable);

    Page<NangmanLetter> findByMemberId(Long memberId, Pageable pageable);

    NangmanLetter findByMemberIdAndId(Long memberId, Long Id);

    Page<NangmanLetter> findByIsPublicTrueAndHasResponseTrue(Pageable pageable);

    NangmanLetter findByIsPublicTrueAndHasResponseTrueAndId(Long id);
}
