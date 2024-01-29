package aromanticcat.umcproject.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "letterbox")
public class Letterbox extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterbox_id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

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
