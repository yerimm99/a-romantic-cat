package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    List<MemberMission> findByMemberId(Long memberId);
    MemberMission findByMemberIdAndMissionId(Long memberId, Long missionId);

    Boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
