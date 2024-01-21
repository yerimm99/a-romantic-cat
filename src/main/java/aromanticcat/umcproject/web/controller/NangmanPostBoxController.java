package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.converter.NangmanPostBoxConverter;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.service.NangmanLetterService;
import aromanticcat.umcproject.service.RandomNicknameService;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;
import aromanticcat.umcproject.web.dto.NangmanPostBoxResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nangman-post")
@RequiredArgsConstructor
public class NangmanPostBoxController {

    private final NangmanLetterService nangmanLetterService;
    private final RandomNicknameService randomNicknameService;

    @GetMapping("/send/random-nickname")
    @ApiOperation(value = "낭만 우편함 편지에 사용될 랜덤 닉네임 생성")
    public ApiResponse<String> getRandomNickname(){
        try {
            //랜덤 닉네임 생성
            String randomNickname = randomNicknameService.generateRandomNickname();

            // 성공 응답 생성
            return ApiResponse.onSuccess(randomNickname);
        } catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
            }
        }


    @PostMapping("/send")
    @ApiOperation(value = "낭만 우편함 편지 작성")
    public ApiResponse<NangmanPostBoxResponseDTO.SendLetterResultDTO> sendLetter(@RequestBody NangmanPostBoxRequestDTO.SendLetterDTO request){
        try{
            //편지 작성 및 발송
            String senderNickname = request.getSenderRandomNickname();
            NangmanLetter nangmanLetter = nangmanLetterService.writeAndSendLetter(request, senderNickname);

            //성공 응답 생성
            return ApiResponse.onSuccess(NangmanPostBoxConverter.toSendLetterResultDTO(nangmanLetter));

        }catch (Exception e){
            //에러 발생 시 실패 응답 반환
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }
}
