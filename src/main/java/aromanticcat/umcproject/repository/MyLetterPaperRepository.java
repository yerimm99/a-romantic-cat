package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letterbox;
import aromanticcat.umcproject.entity.MyLetterPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyLetterPaperRepository extends JpaRepository<MyLetterPaper, Long> {
}
