package aromanticcat.umcproject.service.letterbox;


import aromanticcat.umcproject.entity.*;
import aromanticcat.umcproject.repository.*;
import aromanticcat.umcproject.web.dto.Letterbox.LetterRequest;
import aromanticcat.umcproject.web.dto.Letterbox.LetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LetterService {
    private final LetterRepository letterRepository;
    private final StampRepository stampRepository;
    private final LetterPaperRepository letterPaperRepository;
    private final LetterBoxRepository letterboxRepository;
    private final FriendRepository friendRepository;
    @Transactional
    public Long createLetter(LetterRequest request) {
        Stamp stamp = stampRepository.findById(request.getStampId()).orElse(null);
        LetterPaper letterPaper = letterPaperRepository.findById(request.getLetterPaperId()).orElse(null);
        Letterbox letterbox = letterboxRepository.findById(request.getLetterboxId()).orElse(null);

        if (stamp == null || letterPaper == null || letterbox == null) {
            throw new EntityNotFoundException("not found");
        }

        Friend friend = friendRepository.findByMemberAndFriendId(letterbox.getMember(), request.getSenderId());
        if(friend != null && friend.getFriendStatus() == FriendStatus.APPROVED) {
            int num = friend.updateExchange_num();
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
