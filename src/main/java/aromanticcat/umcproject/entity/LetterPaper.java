package aromanticcat.umcproject.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "letter_paper")
public class LetterPaper extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String imageUrl;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @OneToMany(mappedBy = "letterPaper")
    private List<Letter> letters;

    @Builder
    public LetterPaper(String imageUrl, String name, Integer price) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
    }

}