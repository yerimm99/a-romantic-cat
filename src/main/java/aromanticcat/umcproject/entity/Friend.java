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

    private String friend_name;

    @JoinColumn(name = "to_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long to_user_id;

    private Long from_user_id;

    private boolean are_we_friend;  // 친구인지 여부

    private boolean are_we_close;   // 친한 친구인지 여부

    private int exchange_num;
}
