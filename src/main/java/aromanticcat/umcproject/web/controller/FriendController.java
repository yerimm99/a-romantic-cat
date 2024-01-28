package aromanticcat.umcproject.web.controller;


import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.converter.FriendConverter;
import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.service.FriendService.FriendCommandService;
import aromanticcat.umcproject.web.dto.Friend.FriendRequestDTO;
import aromanticcat.umcproject.web.dto.Friend.FriendResponseDTO;
import aromanticcat.umcproject.service.FriendService.FriendQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address-book")
@RequiredArgsConstructor
public class FriendController {

    private final FriendQueryService friendQueryService;
    private final FriendCommandService friendCommandService;

    @GetMapping("/{member_id}")
    @Operation(summary = "사용자의 주소록에 있는 친구들 조회 API", description = "페이징 포함을 포함합니다, query String으로 page 번호를 주세요.")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1번 페이지 입니다.")
    })
    public ApiResponse<List<FriendResponseDTO.FriendInfoDTO>> getFriendList(@PathVariable(name = "member_id") Long memberId,
                                                                        @RequestParam(value = "page", defaultValue = "0") Integer page){
        try{
            // 페이지별 친구 목록 조회
            Page<Friend> friendList = friendQueryService.getFriendList(memberId, page);

            // 친구 정보를 간략하게 변환
            List<FriendResponseDTO.FriendInfoDTO> friendDTOList = friendList.stream()
                    .map(FriendConverter::toFriendInfoDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            return ApiResponse.onSuccess(friendDTOList);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{member_id}/close-friends")
    @Operation(summary = "사용자의 주소록에 있는 친한 친구들 조회 API", description = "페이징 포함을 포함합니다, query String으로 page 번호를 주세요.")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1번 페이지 입니다.")
    })
    public ApiResponse<List<FriendResponseDTO.FriendInfoDTO>> getCloseFriendList(@PathVariable(name = "member_id") Long memberId,
                                                                            @RequestParam(value = "page", defaultValue = "0") Integer page){
        try{
            // 페이지별 친구 목록 조회
            Page<Friend> friendList = friendQueryService.getCloseFriendList(memberId, page);

            // 친구 정보를 간략하게 변환
            List<FriendResponseDTO.FriendInfoDTO> friendDTOList = friendList.stream()
                    .map(FriendConverter::toFriendInfoDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            return ApiResponse.onSuccess(friendDTOList);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{member_id}/search/friend")
    @Operation(summary = "친구 이름을 통한 검색 API", description = "query String으로 친구 이름을 주세요.")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "friendName", description = "검색하고자 하는 친구의 이름, query string입니다!")
    })
    public ApiResponse<FriendResponseDTO.FriendInfoDTO> getFriendbyName(@PathVariable(name = "member_id") Long memberId,
                                                                        @RequestParam(value = "friendName", defaultValue = "") String friendName){
        try{
            // 친구 이름(닉네임)으로 친구 검색
            Friend friend = friendQueryService.getFriend(memberId, friendName);

            // 친구 내용을 DTO로 간략하게 변환
            FriendResponseDTO.FriendInfoDTO friendInfoDTO = FriendConverter.toFriendInfoDTO(friend);

            // 성공 응답 생성
            return ApiResponse.onSuccess(friendInfoDTO);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @PostMapping("/{member_id}/friend/request")
    @Operation(summary = "친구 추가 API", description = "query String으로 추가하려는 친구의 우편함 번호를 알려주세요.")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "toMemberLetterBoxId", description = "친구 추가하고자 하는 친구의 우편함 번호, query string입니다!")
    })
    public ApiResponse<String> sendFriendRequest(@PathVariable(name = "member_id") Long memberId,
                                                 @RequestBody FriendRequestDTO.FriendshipRequestDTO request){
        try{
            // 친구 요청 보낸기
            friendCommandService.requestFriendship(request);

            // 성공 응답 생성
            return ApiResponse.onSuccess("친구 요청이 성공적으로 보내졌습니다.");

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{member_id}/recieved")
    @Operation(summary = "사용자가 친구 추가 받은 요청 조회 API")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
    })
    public ApiResponse<List<FriendResponseDTO.WaitingFriendDTO>> getReceivedFriendList(@PathVariable(name = "member_id") Long memberId) {
        try{

            // 친구 요청을 보낸 사용자들의 목록 조회
            List<Friend> friendList = friendQueryService.getFriendReceivedList(memberId);

            // 친구 정보를 간략하게 변환
            List<FriendResponseDTO.WaitingFriendDTO> waitingFriendDTOS = friendList.stream()
                    .map(FriendConverter::toWaitingFriendDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            return ApiResponse.onSuccess(waitingFriendDTOS);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{member_id}/recieved")
    @Operation(summary = "사용자가 친구 추가 보낸 요청 조회 API")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
    })
    public ApiResponse<List<FriendResponseDTO.WaitingFriendDTO>> getRequestedFriendList(@PathVariable(name = "member_id") Long memberId) {
        try{

            // 친구 요청을 보낸 사용자들의 목록 조회
            List<Friend> friendList = friendQueryService.getFriendRequestedList(memberId);

            // 친구 정보를 간략하게 변환
            List<FriendResponseDTO.WaitingFriendDTO> waitingFriendDTOS = friendList.stream()
                    .map(FriendConverter::toWaitingFriendDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            return ApiResponse.onSuccess(waitingFriendDTOS);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @PostMapping("/{member_id}/request/approve")
    @Operation(summary = "친구 요청 수락 API", description = "query String으로 친구 요청을 보낸 친구의 아이디를 알려주세요")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "friendId", description = "친구 추가 요청을 보낸 친구의 아이디, query string입니다!")
    })
    public ApiResponse<String> approveFriendRequest(@PathVariable(name = "member_id") Long memberId,
                                                    @RequestParam Long friendId){
        try{
            // 친구 요청 보낸기
            friendCommandService.approveFriendship(memberId, friendId);

            // 성공 응답 생성
            return ApiResponse.onSuccess("친구 요청이 성공적으로 수락되었습니다.");

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @PostMapping("/{member_id}/request/reject")
    @Operation(summary = "친구 요청 거절 API", description = "query String으로 친구 요청을 보낸 친구의 아이디를 알려주세요")
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "friendId", description = "친구 추가 요청을 보낸 친구의 아이디, query string입니다!")
    })
    public ApiResponse<String> rejectFriendRequest(@PathVariable(name = "member_id") Long memberId,
                                                   @RequestParam Long friendId){
        try{
            // 친구 요청 보낸기
            friendCommandService.rejectFriendship(memberId, friendId);

            // 성공 응답 생성
            return ApiResponse.onSuccess("친구 요청이 성공적으로 거절 되었습니다.");

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }
}
