package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.service.MemberService;
import aromanticcat.umcproject.web.dto.Member.MemberSignInRequestDto;
import aromanticcat.umcproject.web.dto.Member.MemberSignupRequestDto;
import aromanticcat.umcproject.web.dto.Member.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/join")
    public Long join(@RequestBody MemberSignupRequestDto requestDto){
        return memberService.join(requestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid MemberSignInRequestDto requestDto){
        return memberService.login(requestDto);
    }
}
