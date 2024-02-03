package aromanticcat.umcproject.S3;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class S3Controller {
    private final S3Service s3Service;

//    @PostMapping
//    public String uploadFile(@RequestPart(value = "img") MultipartFile file) throws IOException {
//        String url = s3Service.uploadFile(file);
//        return url;
//    }
}
