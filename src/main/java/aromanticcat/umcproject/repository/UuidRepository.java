package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Stamp;
import aromanticcat.umcproject.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
