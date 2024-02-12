package aromanticcat.umcproject.service;

import aromanticcat.umcproject.repository.StampRepository;
import org.springframework.stereotype.Service;

@Service
public class StampService {
    // 의존성 주입
    private final StampRepository stampRepository;

    public StampService(StampRepository stampRepository) {
        this.stampRepository = stampRepository;
    }
}
