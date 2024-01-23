package aromanticcat.umcproject.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    private String friendName;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long to_user_id;

    private Long from_user_id;

    private boolean isFriend;  // 친구인지 여부

    private boolean isCloseFriend;   // 친한 친구인지 여부

    private int exchange_num;   // 서로 주고 받은 편지 횟수
}
