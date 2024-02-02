package aromanticcat.umcproject.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stepsCompleted;      // 한 미션에서 완료한 단계 수

    @Setter
    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;    // 미션 완료 여부

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "mission_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;

    public void completeStep(){
        stepsCompleted += 1;
    }

}
