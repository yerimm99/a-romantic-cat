package aromanticcat.umcproject.service.MissionService;

import aromanticcat.umcproject.web.dto.Mission.MissionResponseDTO;

import java.util.List;

public interface MissionQueryService {
    List<MissionResponseDTO.MissionInfoDTO> findMissionList(String userEmail);

    MissionResponseDTO.MissionDetailsDTO findMissionDetails(Long missionId);
}
