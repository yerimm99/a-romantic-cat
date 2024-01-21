package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;

public interface NangmanLetterService {

    NangmanLetter writeAndSendLetter(NangmanPostBoxRequestDTO.SendLetterDTO requestDTO, String randomNickname);
//
//    NangmanLetterDTO readOne(Long id);
//
//    void receivedReply(NangmanLetterDTO nangmanLetterDTO);
}
