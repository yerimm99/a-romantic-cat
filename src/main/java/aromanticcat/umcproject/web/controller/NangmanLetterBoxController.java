package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.converter.NangmanLetterBoxConverter;
import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.service.NangmanLetterBoxService;
import aromanticcat.umcproject.service.RandomNicknameService;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxRequestDTO;
import aromanticcat.umcproject.web.dto.nangmanLetterBox.NangmanLetterBoxResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nangman-letterbox")
@RequiredArgsConstructor
public class NangmanLetterBoxController {

    private final NangmanLetterBoxService nangmanLetterBoxService;
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
    public ApiResponse<NangmanLetterBoxResponseDTO.WriteLetterResultDTO> sendLetter(@RequestBody NangmanLetterBoxRequestDTO.WriteLetterDTO request){
        try{
            //편지 작성 및 발송
//            String senderNickname = request.getSenderRandomNickname();
//            NangmanLetter nangmanLetter = nangmanLetterBoxService.writeAndSendLetter(request, senderNickname);
            NangmanLetter nangmanLetter = nangmanLetterBoxService.writeAndSendLetter(request);


            //성공 응답 생성
            return ApiResponse.onSuccess(NangmanLetterBoxConverter.toWriteLetterResultDTO(nangmanLetter));

        }catch (Exception e){
            //에러 발생 시 실패 응답 반환
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/letter-list")
    @ApiOperation(value = "낭만우편함 답장하기 - 편지 목록")
    public ApiResponse<List<NangmanLetterBoxResponseDTO.PreviewLetterResultDTO>> getLetterList(){
        try{
            //편지 목록 조회
            List<NangmanLetter> letterList = nangmanLetterBoxService.getLetterList();

            //편지 내용의 두 줄만 포함하도록 변환
            List<NangmanLetterBoxResponseDTO.PreviewLetterResultDTO> letterSummaryDTOList = letterList.stream()
                    .map(NangmanLetterBoxConverter::toPreviewLetterResultDTO)
                    .collect(Collectors.toList());

            //성공 응답 생성
            return ApiResponse.onSuccess(letterSummaryDTOList);
        }catch (Exception e){
            //에러 발생 시 실패 응답 반환
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);

        }
    }

    @GetMapping("/letter-list/{nangmanLetterId}")
    @ApiOperation(value = "낭만우편함 답장할 편지 조회")
    public ApiResponse<NangmanLetterBoxResponseDTO.SelectedLetterResultDTO> getNangmanLetterInfo(@PathVariable Long nangmanLetterId){
       try{
           // 특정 편지에 대한 정보 조회
           NangmanLetter seledtedLetter = nangmanLetterBoxService.getLetterById(nangmanLetterId);

           // 랜덤 닉네임 생성
           String randomNickname = randomNicknameService.generateRandomNickname();

           //응답 생성
           NangmanLetterBoxResponseDTO.SelectedLetterResultDTO selectedLetterResultDTO = NangmanLetterBoxConverter.toSelectedLetterResultDTO(seledtedLetter, randomNickname);

           return ApiResponse.onSuccess(selectedLetterResultDTO);
       }catch (Exception e){

           // 에러 발생 시 실패 응답 반환
           return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
       }
    }

    @PostMapping("/letter-list/{nangmanLetterId}")
    @ApiOperation(value = "낭만우편함 답장하기 - 편지 발송")
    public ApiResponse<NangmanLetterBoxResponseDTO.WriteReplyResultDTO> sendReply(@PathVariable Long nangmanLetterId, @RequestBody NangmanLetterBoxRequestDTO.WriteReplyDTO request){
        try{
            //답장 작성 및 발송
            NangmanReply nangmanReply = nangmanLetterBoxService.writeAndSendReply(request, nangmanLetterId);

            //성공 응답 생성
            return ApiResponse.onSuccess(NangmanLetterBoxConverter.toWriteReplyResultDTO(nangmanReply));


        } catch(Exception e) {
            // 에러 발생 시 실패 응답 반환
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);

        }
    }
}
