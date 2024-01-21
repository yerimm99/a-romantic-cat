package aromanticcat.umcproject.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "letter_paper")
public class LetterPaper extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letter_paper_id;

    @NotNull
    private String image_url;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "letterPaper")
    private List<Letter> letters;
}