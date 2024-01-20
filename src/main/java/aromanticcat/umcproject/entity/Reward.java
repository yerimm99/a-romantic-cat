package aromanticcat.umcproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Reward extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int type;

    @Column(columnDefinition = "TEXT")
    private String image_url;

    private String name;

    @JoinColumn(name = "mission_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Mission mission;

}
