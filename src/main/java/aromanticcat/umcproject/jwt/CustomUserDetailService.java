package aromanticcat.umcproject.jwt;

import aromanticcat.umcproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// 로그인 요청을 보낸 사용자의 정보(아이디)를 DB에서 찾아주는 역할
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("로그인 아이디가 존재하지 않습니다."));
    }
}
