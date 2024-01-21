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
public class NangmanLetter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String senderNickname;

    @NotNull
    private Boolean isPublic;

    @NotNull
    private String content;

    @NotNull
    @Builder.Default
    private Boolean hasResponse = false;

    private Integer thumbsUpCnt;

    private Integer heartCnt;

    private Integer cryingCnt;

    private Integer cloverCnt;

    private Integer clapCnt;

    private Integer starCnt;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void change(Boolean hasResponse){
        this.hasResponse = hasResponse;
    }
}
