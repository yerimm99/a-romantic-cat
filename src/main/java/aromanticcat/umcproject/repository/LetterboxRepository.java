package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Letterbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterboxRepository extends JpaRepository<Letterbox, Long> {
    //List<Letterbox> findUserEntityByName(String name);

    //Letterbox findUserByEmail(String email);
}
