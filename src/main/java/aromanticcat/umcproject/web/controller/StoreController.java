package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.service.MemberService;
import aromanticcat.umcproject.service.storeService.StoreService;
import aromanticcat.umcproject.web.dto.store.StoreResponseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final MemberService memberService;

    @GetMapping("/user-coin")
    @ApiOperation(value = "사용자 코인 조회 API")
    public ApiResponse<Integer> getUserCoin(){
        try {
            String userEmail = memberService.getUserInfo().getEmail();

            Integer userCoin = storeService.findUserCoin(userEmail);

            return ApiResponse.onSuccess(userCoin);
        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }
    @GetMapping("/letter-papers")
    @ApiOperation(value = "상점 편지지 목록 조회 API",
            notes = "사용자가 이미 구매한 편지지는 가격을 null로 반환합니다. " +
                    "페이징을 포함합니다. query String으로 page(기본값 0)와 pageSize(기본값 16)를 주세요. " +
                    "sort(정렬 방식, 기본값 'latest')를 주세요. 정렬 방식은 'alphabetical', 'popular', " +
                    "'latest', 'low_price', 'high_price' 중 하나입니다.")
    public ApiResponse<Page<StoreResponseDTO.LetterPaperResultDTO>> getAllLetterPaperList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "16") int pageSize,
            @RequestParam(defaultValue = "latest") String sort) {
        try {
            String userEmail = memberService.getUserInfo().getEmail();

            Page<StoreResponseDTO.LetterPaperResultDTO> letterPaperPage = storeService.findLetterPaperList(userEmail, page, pageSize, sort);

            return ApiResponse.onSuccess(letterPaperPage);
        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/stamps")
    @ApiOperation(value = "상점 우표 목록 조회 API",
            notes = "사용자가 이미 구매한 우표는 가격을 null로 반환합니다. " +
                    "페이징을 포함합니다. query String으로 page(기본값 0)와 pageSize(기본값 15)를 주세요. " +
                    "sort(정렬 방식, 기본값 'latest')를 주세요. 정렬 방식은 'alphabetical', 'popular', " +
                    "'latest', 'low_price', 'high_price' 중 하나입니다.")
    public ApiResponse<Page<StoreResponseDTO.StampResultDTO>> getAllStampList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int pageSize,
            @RequestParam(defaultValue = "latest") String sort) {
        try {
            String userEmail = memberService.getUserInfo().getEmail();

            Page<StoreResponseDTO.StampResultDTO> stampPage = storeService.findStampList(userEmail, page, pageSize, sort);

            return ApiResponse.onSuccess(stampPage);
        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @PostMapping("/letter-papers/{letterPaperId}")
    @ApiOperation(value = "편지지 구매 API")
    public ApiResponse<String> purchasedLetterPaper(@PathVariable Long letterPaperId) {
        try {
            String userEmail = memberService.getUserInfo().getEmail();

            storeService.purchasedLetterPaper(userEmail, letterPaperId);

            return ApiResponse.onSuccess("편지지를 성공적으로 구매하였습니다.");

        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);

        }
    }

    @PostMapping("/stamps/{stampId}")
    @ApiOperation(value = "우표 구매 API")
    public ApiResponse<String> purchasedStamp(@PathVariable Long stampId) {
        try {
            String userEmail = memberService.getUserInfo().getEmail();

            storeService.purchasedStamp(userEmail, stampId);

            return ApiResponse.onSuccess("우표를 성공적으로 구매하였습니다.");

        } catch (Exception e) {
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);

        }
    }

    @PostMapping("/upload/letter-paper")
    @ApiOperation(value = "편지지 업로드 API")
    public ApiResponse<Long> uploadLetterPaper(@RequestPart(value = "img") MultipartFile file, @RequestParam("name") String name, @RequestParam("price") int price){
        if (file != null) {
            try {
                return ApiResponse.onSuccess(storeService.uploadLetterPaper(file, name, price));
            } catch (Exception e) {
                return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "file - exist, error", null); //바꾸기
            }
        }
        return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "file dosen't exist", null); //바꾸기
    }

    @PostMapping("/upload/stamp")
    @ApiOperation(value = "우표 업로드 API")
    public ApiResponse<Long> uploadStamp(@RequestPart(value = "img") MultipartFile file, @RequestParam("name") String name, @RequestParam("price") int price){
        if (file != null) {
            try {
                return ApiResponse.onSuccess(storeService.uploadStamp(file, name, price));
            } catch (Exception e) {
                return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "file - exist, error", null); //바꾸기
            }
        }
        return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "file dosen't exist", null); //바꾸기
    }

}
