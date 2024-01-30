package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.converter.MemberConverter;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.service.MemberService;
import aromanticcat.umcproject.web.dto.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService service;

    @PostMapping("/signUp")
    @Operation(summary = "회원가입 API", description = "회원가입 API입니다.")
    public ApiResponse<MemberResponseDTO.MemberJoinResultDTO> signup(@RequestBody String nickname,
                                                                     @RequestParam String email) {
        try {
            Member user = service.createUser(email, nickname);

            return ApiResponse.onSuccess(MemberConverter.toMemberDTO(user));
        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }
}
