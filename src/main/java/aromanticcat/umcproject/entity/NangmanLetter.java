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
    private String sender_nickname;

    @NotNull
    private Boolean is_public;

    @NotNull
    private String content;

    @NotNull
    @Builder.Default
    private Boolean has_response = false;

    private Integer thumbs_up_cnt;

    private Integer heart_cnt;

    private Integer crying_cnt;

    private Integer clover_cnt;

    private Integer clap_cnt;

    private Integer star_cnt;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
