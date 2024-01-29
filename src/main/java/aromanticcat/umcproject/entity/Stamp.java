package aromanticcat.umcproject.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Table(name = "stamp")
@Builder
public class Stamp extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String imageUrl;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    @OneToMany(mappedBy = "stamp")
    private List<Letter> letters;
}