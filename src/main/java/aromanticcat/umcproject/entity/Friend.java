package aromanticcat.umcproject.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    private Long counterpartId;

    private String friendName;

    private Long friendLetterboxId;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String toMemberName;      // 친구 요청을 받은 사용자의 이름

    private Long toMemberId;    // 친구 요청을 받은 사용자의 아이디

    private String fromMemberName;  // 친구 요청을 보낸 사용자의 이름

    private Long fromMemberId;  // 친구 요청을 보낸 사용자의 아이디

    @Enumerated(EnumType.STRING)
    private FriendStatus friendStatus;  // 친구 상태

    private boolean isFrom;     // true이면 fromMember가 false이면 toMember가 친구 요청을 보냄

    private int exchange_num;   // 서로 주고 받은 편지 횟수
}
