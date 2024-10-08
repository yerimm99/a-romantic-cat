package aromanticcat.umcproject.service.MissionService;

import aromanticcat.umcproject.converter.MissionConverter;
import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.MemberMission;
import aromanticcat.umcproject.entity.Mission;
import aromanticcat.umcproject.entity.MissionStatus;
import aromanticcat.umcproject.repository.MemberMissionRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public Member getMember(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. ID: " + email));
    }

    @Override
    @Transactional
    public void stepCompleted(String userEmail, Long missionId) {      // 미션 안에서 step 하나를 완료

        Member member = getMember(userEmail);

        Boolean isExist = memberMissionRepository.existsByMemberAndMissionId(member, missionId);
        Mission mission = missionRepository.findById(missionId).orElse(null);

        if (!isExist) {  // 처음 시작된 미션인 경우
            MemberMission newMemberMission = MissionConverter.toMemberMission(member, mission);
            memberMissionRepository.save(newMemberMission);
        }

        MemberMission memberMission = memberMissionRepository.findByMemberAndMissionId(member, missionId);

        if (memberMission.getMissionStatus() == MissionStatus.COMPLETED) {
            throw new RuntimeException("미션이 이미 완료되었습니다. 미션 ID: " + missionId);
        } else {
            memberMission.completeStep();   // 미션에서 완료한 스텝 1 증가
            if (memberMission.getStepsCompleted() == mission.getSteps()) {    // 미션의 모든 스텝 완료 시
                memberMission.setMissionStatus(MissionStatus.COMPLETED);
                member.addCoin(mission.getCoin());
            }
        }
    }

}
