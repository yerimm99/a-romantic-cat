//package aromanticcat.umcproject.security.jwt;
//
//import java.io.Serializable;
//import javax.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.springframework.data.redis.core.RedisHash;
//import org.springframework.data.redis.core.index.Indexed;
//
//@Getter
//@AllArgsConstructor
//@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 14)
//public class RefreshToken implements Serializable {
//
//    @Id
//    private String id;
//
//    @Indexed
//    private String accessToken;
//
//    private String refreshToken;
//
//    public void updateAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//}
