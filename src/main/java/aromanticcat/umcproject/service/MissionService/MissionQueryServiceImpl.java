package aromanticcat.umcproject.service.MissionService;

import aromanticcat.umcproject.converter.MissionConverter;
import aromanticcat.umcproject.entity.*;
import aromanticcat.umcproject.repository.MemberMissionRepository;
import aromanticcat.umcproject.repository.MemberRepository;
import aromanticcat.umcproject.repository.MissionRepository;
import aromanticcat.umcproject.web.dto.Mission.MissionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public Member getMember(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. ID: " + email));
    }

    @Override
    @Transactional
    public List<MissionResponseDTO.MissionInfoDTO> findMissionList(String userEmail) {

        Member member = getMember(userEmail);

        // 사용자가 참여한 미션 목록 조회
        List<MemberMission> memberMissionList = memberMissionRepository.findByMember(member);

        // 모든 미션 목록 조회
        List<Mission> allMissionList = missionRepository.findAll();

        // DTO 변환
        List<MissionResponseDTO.MissionInfoDTO> responseDTOs = allMissionList.stream()
                .map(mission -> MissionConverter.toMissionInfoDTO(mission, memberMissionList))
                .collect(Collectors.toList());

        return responseDTOs;
    }

    @Override
    @Transactional
    public MissionResponseDTO.MissionDetailsDTO findMissionDetails(Long missionId) {

        Mission mission = missionRepository.findById(missionId).orElse(null);

        if(mission == null){ throw new RuntimeException("미션을 찾을 수 없습니다. ID: " + missionId);}

        MissionResponseDTO.MissionDetailsDTO details = MissionConverter.toMissionDetailsDTO(mission);

        return details;
    }
}
