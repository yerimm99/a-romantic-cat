package aromanticcat.umcproject.converter;

import aromanticcat.umcproject.entity.*;
import aromanticcat.umcproject.web.dto.Mission.MissionResponseDTO;
import aromanticcat.umcproject.web.dto.store.StoreResponseDTO;
import io.swagger.models.auth.In;

import java.util.List;

public class MissionConverter {

    public static MissionResponseDTO.MissionInfoDTO toMissionInfoDTO(Mission mission, List<MemberMission> memberMissionList){

        // memberMissionList에서 mission이 있는 MemberMission 반환
        MemberMission memberMission =  isMissionStarted(mission, memberMissionList);

        // isMissionStarted가 null이면 stepsCompleted에 0 대입
        Integer currentStepsCompleted = memberMission != null ? memberMission.getStepsCompleted() : 0;

        // isMissionStarted가 null이면 missionStatus에 0 대입
        MissionStatus currentMissionStatus = memberMission != null ? memberMission.getMissionStatus() : MissionStatus.NOT_STARTED;

        return MissionResponseDTO.MissionInfoDTO.builder()
                .missionId(mission.getId())
                .name(mission.getName())
                .steps(mission.getSteps())
                .stepsCompleted(currentStepsCompleted)
                .missionStatus(currentMissionStatus)
                .build();
    }

    public static MissionResponseDTO.MissionDetailsDTO toMissionDetailsDTO(Mission mission){

        return MissionResponseDTO.MissionDetailsDTO.builder()
                .missionId(mission.getId())
                .name(mission.getName())
                .content(mission.getContent())
                .coin(mission.getCoin())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .stepsCompleted(0)
                .missionStatus(MissionStatus.ONGOING)
                .build();
    }

    private static MemberMission isMissionStarted(Mission mission, List<MemberMission> memberMissionList) {

        //  주어진 미션의 미션 아이디
        Long missionId = mission.getId();

        // memberMissionList에서 missionId가 있는 MemberMission 반환
        return memberMissionList.stream()
                .filter(memberMission -> memberMission.getMission() != null && memberMission.getMission().getId().equals(missionId))
                .findFirst().orElse(null);

    }

}
