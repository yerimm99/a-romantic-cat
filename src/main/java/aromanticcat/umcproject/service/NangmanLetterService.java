package aromanticcat.umcproject.service;

import aromanticcat.umcproject.dto.NangmanLetterDTO;

public interface NangmanLetterService {

    NangmanLetterDTO register(NangmanLetterDTO nangmanLetterDTO);

    NangmanLetterDTO readOne(Long id);

    void receivedReply(NangmanLetterDTO nangmanLetterDTO);
}
