package aromanticcat.umcproject.service.letterbox;


import aromanticcat.umcproject.entity.*;
import aromanticcat.umcproject.repository.*;
import aromanticcat.umcproject.web.dto.Letterbox.LetterRequest;
import aromanticcat.umcproject.web.dto.Letterbox.LetterResponse;
import aromanticcat.umcproject.web.dto.Letterbox.LetterboxRequest;
import aromanticcat.umcproject.web.dto.Letterbox.LetterboxResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LetterService {
    private final LetterRepository letterRepository;
    private final StampRepository stampRepository;
    private final LetterPaperRepository letterPaperRepository;
    private final LetterBoxRepository letterboxRepository;

    @Transactional
    public Long createLetter(LetterRequest request) {
        Stamp stamp = stampRepository.findById(request.getStampId()).orElse(null);
        LetterPaper letterPaper = letterPaperRepository.findById(request.getLetterPaperId()).orElse(null);
        Letterbox letterbox = letterboxRepository.findById(request.getLetterboxId()).orElse(null);
        //여기에 로그인 + 친구 유저는 편지 교환 횟수 +1 코드
        if (stamp == null || letterPaper == null || letterbox == null) {
            throw new EntityNotFoundException("not found");
        }

        Letter letter = request.toEntity();
        letter.setStamp(stamp);
        letter.setLetterPaper(letterPaper);
        letter.setLetterbox(letterbox);
        Letter savedLetter = letterRepository.save(letter);
        return savedLetter.getLetterId();
    }

    public LetterResponse getLetterById(Long letterId) {
        Letter letter = letterRepository.findByLetterId(letterId).orElse(null);

        if (letter != null) {
            System.out.println(letter.toString());
            return letter.toResponse(letter);
        } else {
            return null;
        }
    }

}
