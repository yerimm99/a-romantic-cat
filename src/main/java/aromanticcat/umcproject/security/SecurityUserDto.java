//package aromanticcat.umcproject.security;
//
//import static aromanticcat.umcproject.security.Role.USER;
//
//import aromanticcat.umcproject.entity.Member;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class SecurityUserDto {
//
//    private String nickname;
//    private String email;
//    private Role role;
//
//    public Member toEntity() {
//        return Member.builder()
//                .email(email)
//                .nickname(nickname)
//                .userRole(USER)
//                .build();
//    }
//}
