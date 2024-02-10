package aromanticcat.umcproject.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "slow_letter")
public class SlowLetter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slowLetterId;

    @Column(name = "date")
    private LocalDate date;

    @Column(length = 255)
    private String content;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public SlowLetter(LocalDate date, String content) {
        this.date = date;
        this.content = content;
    }
    public void updateMember(Member member) {
        this.member = member;
    }
}
