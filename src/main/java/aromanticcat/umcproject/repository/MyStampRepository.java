package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letterbox;
import aromanticcat.umcproject.entity.MyStamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStampRepository extends JpaRepository<MyStamp, Long> {
}
