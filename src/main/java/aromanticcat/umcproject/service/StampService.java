package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.Stamp;
import aromanticcat.umcproject.repository.StampRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StampService {
    // 의존성 주입
    private final StampRepository stampRepository;

    public StampService(StampRepository stampRepository) {
        this.stampRepository = stampRepository;
    }
}
