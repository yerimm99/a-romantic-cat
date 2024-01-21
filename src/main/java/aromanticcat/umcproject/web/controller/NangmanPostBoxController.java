package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.converter.NangmanPostBoxConverter;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.service.NangmanPostBoxService;
import aromanticcat.umcproject.service.RandomNicknameService;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;
import aromanticcat.umcproject.web.dto.NangmanPostBoxResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nangman-post")
@RequiredArgsConstructor
public class NangmanPostBoxController {

    private final NangmanPostBoxService nangmanPostBoxService;
    private final RandomNicknameService randomNicknameService;

    @GetMapping("/send/random-nickname")
    @ApiOperation(value = "낭만우편함 고민 편지에 사용될 랜덤 닉네임 생성")
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
    @ApiOperation(value = "낭만우편함 고민 편지 발송")
    public ApiResponse<NangmanPostBoxResponseDTO.SendLetterResultDTO> sendLetter(@RequestBody NangmanPostBoxRequestDTO.SendLetterDTO request){
        try{
            //편지 작성 및 발송
            String senderNickname = request.getSenderRandomNickname();
            NangmanLetter nangmanLetter = nangmanPostBoxService.writeAndSendLetter(request, senderNickname);

            //성공 응답 생성
            return ApiResponse.onSuccess(NangmanPostBoxConverter.toSendLetterResultDTO(nangmanLetter));

        }catch (Exception e){
            //에러 발생 시 실패 응답 반환
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/letter-list")
    @ApiOperation(value = "낭만우편함 답장하기 - 편지 목록")
    public ApiResponse<List<NangmanPostBoxResponseDTO.LetterSummaryResultDTO>> getLetterList(){
        try{
            //편지 목록 조회
            List<NangmanLetter> letterList = nangmanPostBoxService.getLetterList();

            //편지 내용의 두 줄만 포함하도록 변환
            List<NangmanPostBoxResponseDTO.LetterSummaryResultDTO> letterSummaryDTOList = letterList.stream()
                    .map(NangmanPostBoxConverter::toLetterSummaryResultDTO)
                    .collect(Collectors.toList());

            //성공 응답 생성
            return ApiResponse.onSuccess(letterSummaryDTOList);
        }catch (Exception e){
            //에러 발생 시 실패 응답 반환
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);

        }
    }

    @GetMapping("/letter-list/{nangmanLetterId}")
    @ApiOperation(value = "낭만우편함 답장하기 - 편지 선택")
    public ApiResponse<NangmanPostBoxResponseDTO.SelectedLetterResultDTO> getNangmanLetterInfo(@PathVariable Long nangmanLetterId){
       try{
           // 특정 편지에 대한 정보 조회
           NangmanLetter seledtedLetter = nangmanPostBoxService.getLetterById(nangmanLetterId);

           // 랜덤 닉네임 생성
           String randomNickname = randomNicknameService.generateRandomNickname();

           //응답 생성
           NangmanPostBoxResponseDTO.SelectedLetterResultDTO selectedLetterResultDTO = NangmanPostBoxConverter.toReplyLetterResultDTO(seledtedLetter, randomNickname);

           return ApiResponse.onSuccess(selectedLetterResultDTO);
       }catch (Exception e){

           // 에러 발생 시 실패 응답 반환
           return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
       }
    }

//
//    @PostMapping("/letter-list/{nangmanLetterId}")
//    @ApiOperation(value = "낭만우편함 답장하기 - 편지 발송")
}
