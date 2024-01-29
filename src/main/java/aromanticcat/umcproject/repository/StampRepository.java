package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letter;
import aromanticcat.umcproject.entity.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    Optional<Stamp> findById(Long id);
    Page<Stamp> findAll(Pageable pageable);
}
