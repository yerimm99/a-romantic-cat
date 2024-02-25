package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.FriendStatus;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.jwt.JwtTokenProvider;
import aromanticcat.umcproject.jwt.SecurityUtil;
import aromanticcat.umcproject.repository.FriendRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.web.dto.Member.MemberSignInRequestDto;
import aromanticcat.umcproject.web.dto.Member.MemberSignupRequestDto;
import aromanticcat.umcproject.web.dto.Member.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;
    private final FriendRepository friendRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Optional<Member> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    /**
     * 입력한 이메일이 이미 가입된 이메일은 아닌지 확인합니다. (이메일은 unique 해야 합니다)
     * 가입할 수 있는 이메일이라면 저장한 후 USER 권한을 부여합니다.
     * 비밀번호를 암호화시킵니다.
     */

    @Transactional
    @Override
    public Long join(MemberSignupRequestDto requestDto){
        if(repository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        Member member = repository.save(requestDto.toEntity());
        member.addUserAuthority();
        member.encodePassword(passwordEncoder);
        return member.getId();
    }

    /**
     * 입력한 이메일과 비밀번호가 일치하는지 확인합니다.
     * 맞다면 Access Token, Refresh Token 을 반환합니다.
     * 이 때, Refresh Token 은 DB에 저장합니다.
     */

    @Transactional
    @Override
    public TokenResponseDto login(MemberSignInRequestDto requestDto) {
        Member member = repository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입된 이메일이 아닙니다."));
        validateMatchedPassword(requestDto.getPassword(), member.getPassword());

        String accessToken = jwtTokenProvider.createAccessToken(member.getUsername(), member.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        member.updateRefreshToken(refreshToken);
        repository.save(member);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    @Override
    public TokenResponseDto issueAccessToken(HttpServletRequest request){
        // 만료된 accessToken과 refreshToken을 가져옴
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        //accessToken이 만료되었으면
        if(jwtTokenProvider.validateAccessToken(accessToken)){
            log.info("access 토큰 만료됨");
            //만약 refreshToken이 유효하다면
            if(jwtTokenProvider.validateRefreshToken(refreshToken)){
                log.info("refresh 토큰은 유효합니다.");

                //DB에 저장해두었던 refreshToken을 불러오고, 새로운 AccessToken을 생성하기 위함
                Member member = repository.findByEmail(jwtTokenProvider.getUserEmail(refreshToken))
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

                // 만약 DB refreshToken 와 요청한 refreshToken이 같다면
                if(refreshToken.equals(member.getRefreshToken())){
                    //새로운 accessToken 생성
                    accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());

                } else{
                    log.info("토큰이 변조되었습니다.");
                }
            } else {
                log.info("Refresh 토큰이 유효하지 않습니다.");
            }
        }

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Member validateLoginStatus(){
        return repository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("로그인 하세요."));
    }

    private void validateMatchedPassword(String validPassword, String memberPassword){
        if(!passwordEncoder.matches(validPassword, memberPassword)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

//    @Override
//    public Member createUser(String email, String nickname) {
//        isNicknameExist(nickname);
//        isNicknameUnique(nickname);
//
//        Member newUser = Member.builder()
//                .email(email)
//                .nickname(nickname)
//                .userRole(Role.USER)
//                .build();
//
//        return repository.save(newUser);
//    }

//    @Override
//    public void isNicknameExist(String nickname) {
//        if (nickname.isEmpty()) {
//            throw new MemberHandler(ErrorStatus.NICKNAME_NOT_EXIST);
//        }
//    }
//
//    @Override
//    public void isNicknameUnique(String nickname) {
//        Optional<Member> existingMember = repository.findByNickname(nickname);
//        if (existingMember.isPresent()) {
//            throw new MemberHandler(ErrorStatus.NICKNAME_ALREADY_EXIST);
//        }
//    }

//    @Override
//    public SecurityUserDto getUserInfo() {
//        return JwtAuthFilter.getUser();
//    }


//    @Override
//    public Member updateNickname(String newNickname) {
//        isNicknameExist(newNickname);
//        isNicknameUnique(newNickname);
//
//        //Security context로부터 user 정보 받아옴
//        Optional<Member> memberOptional = findByEmail(getUserInfo().getEmail());
//        if (memberOptional.isPresent()) {
//            Member member = memberOptional.get();
//            // 닉네임 업데이트
//            member.setNickname(newNickname);
//            // 변경된 멤버 저장 후 반환
//            return repository.save(member);
//        } else {
//            // 해당 이메일에 해당하는 회원이 없을 경우
//            throw new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND);
//        }
//    }

    @Override
    public Member findByMemberId(Long findMemberId, String userEmail) {

        Member member = findByEmail(userEmail).orElse(null);

        // 이미 존재하는 친구인지를 확인하기 위함
        Set<FriendStatus> friendStatus = new HashSet<>();
        friendStatus.add(FriendStatus.APPROVED);
        friendStatus.add(FriendStatus.CLOSE_FRIEND);
        Boolean isFriend = friendRepository.existsByMemberAndFriendIdAndFriendStatus(member, findMemberId, friendStatus);

        if(isFriend){   // 이미 친구인 경우 예외 처리
            throw new IllegalArgumentException("이미 친구인 사용자입니다. 친구 아이디: " + findMemberId);
        } else {
            return repository.findById(findMemberId).orElse(null);     // 사용자가 검색하려는 멤버 ID
        }
    }



}
