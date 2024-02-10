package aromanticcat.umcproject.repository;

import aromanticcat.umcproject.entity.Member;
import aromanticcat.umcproject.entity.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    List<MemberMission> findByMember(Member member);
    MemberMission findByMemberAndMissionId(Member member, Long missionId);

    Boolean existsByMemberAndMissionId(Member member, Long missionId);
}
