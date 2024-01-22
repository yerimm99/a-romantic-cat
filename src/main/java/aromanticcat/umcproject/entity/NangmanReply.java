package aromanticcat.umcproject.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NangmanReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String replySenderNickname;

    @NotNull
    private String content;

    @JoinColumn(name = "letter_id")
    @OneToOne(fetch = FetchType.LAZY)
    private NangmanLetter nangmanLetter;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
