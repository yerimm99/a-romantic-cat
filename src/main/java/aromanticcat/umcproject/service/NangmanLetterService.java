package aromanticcat.umcproject.service;

import aromanticcat.umcproject.dto.NangmanLetterDTO;

public interface NangmanLetterService {

    Long register(NangmanLetterDTO nangmanLetterDTO);

    NangmanLetterDTO readOne(Long id);

    void modify(NangmanLetterDTO nangmanLetterDTO);
}
