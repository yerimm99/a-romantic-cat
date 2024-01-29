package aromanticcat.umcproject.entity;

import aromanticcat.umcproject.web.dto.Letterbox.LetterResponse;
import aromanticcat.umcproject.web.dto.Letterbox.LetterboxResponse;
import lombok.Getter;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "letter")
public class Letter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letterId;

    @Column(name = "nickname", length = 60)
    private String nickname;

    @Column(length = 255)
    private String content;

    private Boolean open;

    @Column(name = "login_status")
    private Boolean loginStatus;

    @Column(name = "sender_ID")
    private Long senderId;

    @ManyToOne
    @JoinColumn(name = "letter_paper_id")
    private LetterPaper letterPaper;

    @ManyToOne
    @JoinColumn(name = "stamp_id")
    private Stamp stamp;

    @ManyToOne
    @JoinColumn(name = "id")
    private Letterbox letterbox;


    @Builder
    public Letter(String nickname, String content, boolean open, Boolean loginStatus, Long senderId) {
        this.nickname = nickname;
        this.content = content;
        this.open = open;
        this.loginStatus = loginStatus;
        this.senderId = senderId;
    }

    public LetterResponse toResponse(Letter letter) {
        LetterResponse letterResponse = new LetterResponse();
        letterResponse.setNickname(letter.nickname);
        letterResponse.setContent(letter.content);
        letterResponse.setOpen(letter.open);
        letterResponse.setLoginStatus(letter.loginStatus);
        letterResponse.setSenderId(letter.senderId);
        letterResponse.setLetterboxId(letter.letterbox.getLetterbox_id());
        letterResponse.setLetterPaperId(letter.letterPaper.getId());
        letterResponse.setStampId(letter.stamp.getId());

        return letterResponse;
    }
}
