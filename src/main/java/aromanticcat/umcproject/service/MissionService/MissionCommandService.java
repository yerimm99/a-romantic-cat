package aromanticcat.umcproject.service.MissionService;

import aromanticcat.umcproject.entity.Member;

public interface MissionCommandService {
    void stepCompleted(String userEmail, Long missionId);

//    void resetDailyMissions();
}
