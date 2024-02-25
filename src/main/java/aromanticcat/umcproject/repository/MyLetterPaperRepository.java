package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.MyLetterPaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyLetterPaperRepository extends JpaRepository<MyLetterPaper, Long> {
    Page<MyLetterPaper> findByMemberEmail(String email, Pageable pageable);

    Page<MyLetterPaper> findByMemberId(Long id, Pageable pageable);


    Optional<List<MyLetterPaper>> findMyLetterPapersByMember(Member member);
}
