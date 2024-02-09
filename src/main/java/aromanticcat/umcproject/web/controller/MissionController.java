package aromanticcat.umcproject.web.controller;

import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.service.MissionService.MissionCommandService;
import aromanticcat.umcproject.service.MissionService.MissionQueryService;
import aromanticcat.umcproject.web.dto.Mission.MissionResponseDTO;
import aromanticcat.umcproject.web.dto.nangmanLetterbox.NangmanCollectionResponseDTO;
import aromanticcat.umcproject.web.dto.store.StoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @GetMapping("/")
    @Operation(summary = "미션 목록 조회 API", description = "모든 미션을 조회합니다.")
    public ApiResponse<List<MissionResponseDTO.MissionInfoDTO>> getAllMissions() {
        try{
            // 로그인한 사용자의 아이디를 가져오는 임시 메서드
            Long memberId = getCurrentUserId();

            // 모든 미션 가져오기
            List<MissionResponseDTO.MissionInfoDTO> MissionList = missionQueryService.findMissionList(memberId);

            // 성공 응답 가져오기
            return ApiResponse.onSuccess(MissionList);

        } catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{missionId}")
    @Operation(summary = "미션 목록에서 특정 미션 상세 조회 API", description = "미션 내용, 미션 성공 시 획득 하는 코인 수")
    public ApiResponse<MissionResponseDTO.MissionDetailsDTO> MissionDetails(@PathVariable Long missionId){
        try{

            MissionResponseDTO.MissionDetailsDTO details = missionQueryService.findMissionDetails(missionId);

            return ApiResponse.onSuccess(details);

        } catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }

    }

    @PostMapping("/{missionId}")
    @Operation(summary = "미션 한 단계 완료 API", description = "특정 미션의 한 스텝이 완료된 것을 적용합니다. 미션의 모든 단계가 완료 되었으면 보상으로 코인이 주어집니다.")
    public ApiResponse<String> MissionStepCompleted(@PathVariable Long missionId){
        try{
            // 로그인 한 사용자의 아이디를 가져오는 임시 메서드
            Long memberId = getCurrentUserId();

            missionCommandService.stepCompleted(memberId, missionId);

            return ApiResponse.onSuccess("미션의 한 단계 완료가 성공적으로 적용되었습니다.");

        } catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    // 스프링 시큐리티 구현 전 임시 메서드
    private Long getCurrentUserId(){
        return 1L;
    }
}
