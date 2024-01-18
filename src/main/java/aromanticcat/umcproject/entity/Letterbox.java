package aromanticcat.umcproject.entity;

import lombok.Getter;

import javax.persistence.*;
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

    private String name;

    private String color;

    @Column(name = "end_dt")
    private LocalDateTime endDt;

    private Boolean activate;

    private Boolean sender;
}
