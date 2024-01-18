package aromanticcat.umcproject.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class NangmanLetter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender_nickname;

    private boolean is_public;

    private String content;

    private boolean has_response;

    private int thumbs_up_cnt;

    private int heart_cnt;

    private int crying_cnt;

    private int clover_cnt;

    private int clap_cnt;

    private int star_cnt;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
