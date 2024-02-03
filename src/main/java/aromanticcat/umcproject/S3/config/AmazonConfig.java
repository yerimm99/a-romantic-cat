package aromanticcat.umcproject.S3.config;

import lombok.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.path.review}")
    private String bucket;
}
