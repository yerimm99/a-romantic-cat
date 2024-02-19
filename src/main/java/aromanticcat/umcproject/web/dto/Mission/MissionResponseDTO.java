package aromanticcat.umcproject.web.dto.Mission;

import aromanticcat.umcproject.entity.MissionStatus;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionInfoDTO{

        Long missionId;        // 미션 아이디
        String name;    // 미션 이름
        Integer steps;      // 미션의 총 단계 수
        boolean everyday;
        Integer stepsCompleted;     // 한 미션에서 사용자가 완료한 미션의 수
        MissionStatus missionStatus;    // 미션 완료 여부

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDetailsDTO{

        Long missionId;     // 미션 아이디
        String name;        // 미션 이름
        String content;     // 미션 상세 내용
        Integer coin;       // 미션 성공 시 획득하는 코인 수
    }
}
