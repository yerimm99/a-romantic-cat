package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.web.dto.NangmanLetterDTO;
import aromanticcat.umcproject.service.NangmanLetterService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nangman-post")
@RequiredArgsConstructor
public class NangmanLetterController {

    private final NangmanLetterService nangmanLetterService;

    @PostMapping("/send")
    @ApiOperation(value = "낭만 우편함 편지 등록")
    public ApiResponse<NangmanLetterDTO> send(@RequestBody NangmanLetterDTO nangmanLetterDTO){
        try{
            //낭만 우편함 편지 작성 및 응답 DTO 변환
            NangmanLetterDTO responseDTO  = nangmanLetterService.register(nangmanLetterDTO);

            //전송 성공 응답 반환
            return ApiResponse.onSuccess(responseDTO);

        }catch (Exception e){
            //에러 발생 시 실패 응답 반환
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }
}
