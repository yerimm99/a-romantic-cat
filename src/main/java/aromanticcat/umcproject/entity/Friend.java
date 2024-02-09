package aromanticcat.umcproject.entity;

import javax.persistence.*;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;    // 친구 요청 정보에 관한 아이디

    @Setter
    private Long counterpartId;     // 상대방 측 친구 요청 정보에 관한 아이디

    private String friendName;

    private Long friendId;  // 친구 아이디

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


    public void changeFriendStatus(FriendStatus status){
        this.friendStatus = status;
    }

}
