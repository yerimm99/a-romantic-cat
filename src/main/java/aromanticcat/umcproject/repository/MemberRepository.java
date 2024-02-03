package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letterbox;
import aromanticcat.umcproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
}
