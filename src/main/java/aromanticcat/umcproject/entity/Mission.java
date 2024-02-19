package aromanticcat.umcproject.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import io.swagger.models.auth.In;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;    // 미션 제목

    private String content;     // 미션 내용

    private Integer steps;      // 미션의 총 단계

    private Integer coin;       // 미션 성공 시 보상으로 받을 코인 수

    private boolean everyday;

    @OneToMany(mappedBy = "mission", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions = new ArrayList<>();
}
