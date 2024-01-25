package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.converter.MemberConverter;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.service.MemberService;
import aromanticcat.umcproject.web.dto.MemberResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService service;

    @PostMapping("/signUp")
    public ApiResponse<MemberResponseDTO.MemberJoinResultDTO> signup(@RequestBody String nickname,
                                                                     @RequestParam String email) {

        Member user = service.createUser(email, nickname);

        return ApiResponse.onSuccess(MemberConverter.toMemberDTO(user));
    }
}
