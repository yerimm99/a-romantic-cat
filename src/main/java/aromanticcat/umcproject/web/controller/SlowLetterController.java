package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.entity.SlowLetter;
import aromanticcat.umcproject.service.slowLetter.SlowLetterService;
import aromanticcat.umcproject.web.dto.SlowLetterRequest;
import aromanticcat.umcproject.web.dto.SlowLetterResponse;
import io.swagger.annotations.ApiOperation;
import aromanticcat.umcproject.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/slow_letters")
public class SlowLetterController {
    private final SlowLetterService slowLetterService;

    @PostMapping("/{memberId}")
    @ApiOperation(value = "느린 편지 생성")
    public ApiResponse<SlowLetterResponse> createApplicant(@PathVariable Long memberId, @RequestBody SlowLetterRequest request){
        try {
            SlowLetter slowLetter = slowLetterService.save(memberId, request);
            return ApiResponse.onSuccess(SlowLetterResponse.from(slowLetter));
        } catch (Exception e){
        return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
    }
    }
}
