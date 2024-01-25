package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letterbox;
import aromanticcat.umcproject.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {

}
