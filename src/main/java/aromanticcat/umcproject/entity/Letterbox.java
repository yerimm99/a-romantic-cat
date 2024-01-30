package aromanticcat.umcproject.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "letterbox")
public class Letterbox extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterbox_id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    private String name;
    @NotNull
    private String color;

    @NotNull
    @Column(name = "end_dt")
    private LocalDateTime endDt;

    @NotNull
    private Boolean activate;

    @NotNull
    private Boolean sender;

    @OneToMany(mappedBy = "letterbox")
    private List<Letter> letters;
}
