package aromanticcat.umcproject.web.controller;


import aromanticcat.umcproject.apiPayload.ApiResponse;
import aromanticcat.umcproject.converter.FriendConverter;
import aromanticcat.umcproject.entity.Friend;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.service.FriendService.FriendCommandService;
import aromanticcat.umcproject.web.dto.Friend.FriendRequestDTO;
import aromanticcat.umcproject.web.dto.Friend.FriendResponseDTO;
import aromanticcat.umcproject.service.FriendService.FriendQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1번 페이지 입니다.")
    })
    public ApiResponse<List<FriendResponseDTO.FriendDTO>> getFriendList(@PathVariable(name = "member_id") Long memberId,
                                                                        @RequestParam(value = "page", defaultValue = "0") Integer page){
        try{
            // 페이지별 친구 목록 조회
            Page<Friend> friendList = friendQueryService.getFriendList(memberId, page);

            // 친구 내용을 간략하게 변환
            List<FriendResponseDTO.FriendDTO> friendDTOList = friendList.stream()
                    .map(FriendConverter::toFriendDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            return ApiResponse.onSuccess(friendDTOList);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{member_id}/close-friends")
    @Operation(summary = "사용자의 주소록에 있는 친한 친구들 조회 API", description = "페이징 포함을 포함합니다, query String으로 page 번호를 주세요.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1번 페이지 입니다.")
    })
    public ApiResponse<List<FriendResponseDTO.FriendDTO>> getCloseFriendList(@PathVariable(name = "member_id") Long memberId,
                                                                            @RequestParam(value = "page", defaultValue = "0") Integer page){
        try{
            // 페이지별 친구 목록 조회
            Page<Friend> friendList = friendQueryService.getCloseFriendList(memberId, page);

            // 친구 내용을 간략하게 변환
            List<FriendResponseDTO.FriendDTO> friendDTOList = friendList.stream()
                    .map(FriendConverter::toFriendDTO)
                    .collect(Collectors.toList());

            // 성공 응답 생성
            return ApiResponse.onSuccess(friendDTOList);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @GetMapping("/{member_id}/search/friend")
    @Operation(summary = "친구 이름을 통한 검색 API", description = "query String으로 친구 이름을 주세요.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "friendName", description = "검색하고자 하는 친구의 이름, query string입니다!")
    })
    public ApiResponse<FriendResponseDTO.FriendDTO> getFriend(@PathVariable(name = "member_id") Long memberId,
                                                              @RequestParam(value = "friendName", defaultValue = "") String friendName){
        try{
            // 친구 이름(닉네임)으로 친구 검색
            Friend friend = friendQueryService.getFriend(memberId, friendName);

            // 친구 내용을 DTO로 간략하게 변환
            FriendResponseDTO.FriendDTO friendDTO = FriendConverter.toFriendDTO(friend);

            // 성공 응답 생성
            return ApiResponse.onSuccess(friendDTO);

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }

    @PostMapping("/{member_id}/friend/request")
    @Operation(summary = "친구 추가 API", description = "query String으로 추가하려는 친구의 우편함 번호를 알려주세요.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "toMemberLetterBoxId", description = "친구 추가하고자 하는 친구의 우편함 번호, query string입니다!")
    })
    public ApiResponse<FriendResponseDTO.friendshipRequestResultDTO> sendFriendRequest(@PathVariable(name = "member_id") Long memberId,
                                                                                       @RequestBody FriendRequestDTO.FriendshipRequestDTO request){
        try{

            // 친구 요청을 보내는 사용자
            Member fromMember = friendCommandService.getMember(memberId);
            // 친구 요청을 받는 사용자
            Member toMember = friendCommandService.getMemberByLetterBoxId(request.getToMemberLetterBoxId());

            // DTO의 정보를 기반으로 친구 객체 형성(toStore)
            Friend friend = Friend.builder()
                    .member(fromMember)
                    .fromMemberName(fromMember.getName())
                    .toMemberId(toMember.getId())
                    .toMemberName(toMember.getName())


            return null;

        }catch (Exception e){
            return ApiResponse.onFailure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), null);
        }
    }




}
