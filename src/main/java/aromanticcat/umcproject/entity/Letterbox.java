package aromanticcat.umcproject.entity;

import antlr.collections.List;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "letterbox")
public class Letterbox extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterbox_id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

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
