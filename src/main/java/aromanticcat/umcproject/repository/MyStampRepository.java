package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.MyLetterPaper;
import aromanticcat.umcproject.entity.MyStamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyStampRepository extends JpaRepository<MyStamp, Long> {

    Page<MyStamp> findByMemberEmail(String email, Pageable pageable);

    Optional<List<MyStamp>> findMyStampsByMember(Member member);
}
