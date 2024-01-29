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

    //@Autowired
    //private UploadS3Service uploadS3Service;

    public StampService(StampRepository stampRepository) {
        this.stampRepository = stampRepository;
    }

//    @Transactional
//    public Long keepDiary(MultipartFile image, Stamp stamp) throws IOException {
//        if(!image.isEmpty()) {
//            String storedFileName = uploadS3Service.upload(image,"images");
//            stamp.setImageUrl(storedFileName);
//        }
//        Stamp newStamp = stampRepository.save(stamp);
//        return newStamp.getId();
//    }
}
