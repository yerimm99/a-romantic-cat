package aromanticcat.umcproject.service;

import aromanticcat.umcproject.entity.NangmanLetter;
import aromanticcat.umcproject.entity.NangmanReply;
import aromanticcat.umcproject.web.dto.NangmanPostBoxRequestDTO;

import java.util.List;

public interface NangmanPostBoxService {

    NangmanLetter writeAndSendLetter(NangmanPostBoxRequestDTO.SendLetterDTO requestDTO);

    List<NangmanLetter> getLetterList();

    NangmanLetter getLetterById(Long id);

    NangmanReply writeAndSendReply(NangmanPostBoxRequestDTO.ReplyLetterDTO requestDTO, Long id);

//    NangmanLetterDTO readOne(Long id);
//
//    void receivedReply(NangmanLetterDTO nangmanLetterDTO);
}
