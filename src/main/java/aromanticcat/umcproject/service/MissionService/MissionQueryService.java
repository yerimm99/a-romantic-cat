package aromanticcat.umcproject.service.MissionService;

import aromanticcat.umcproject.web.dto.Mission.MissionResponseDTO;

import java.util.List;

public interface MissionQueryService {
    List<MissionResponseDTO.MissionInfoDTO> findMissionList(Long memberId);

    MissionResponseDTO.MissionDetailsDTO findMissionDetails(Long missionId);
}
